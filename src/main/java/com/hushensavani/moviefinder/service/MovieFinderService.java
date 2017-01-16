package com.hushensavani.moviefinder.service;

import com.hushensavani.moviefinder.model.Movie;
import com.hushensavani.moviefinder.utils.ApplicationConfigUtil;
import com.hushensavani.moviefinder.utils.CacheUtils;
import com.hushensavani.moviefinder.utils.DirectoryScanner;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created on: 14-January-2017
 *
 * @author <a href="mailto:husen.savani1@gmail.com">Hushen Savani</a>
 *
 * @description This is primary service responsible for fetching movie data from OMDB (The Open Movies Database).
 *
 */
public class MovieFinderService {

    private static final String OMDB_API_URL = "http://www.omdbapi.com/";
    private static Hashtable config;

    static {
        config = ApplicationConfigUtil.getInstance().getConfigHT("config");
    }

    /**
     * The Main()
     */
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        String mediaRootPath = (String) config.get("media.root.path");
        List<Movie> moviesList = findMovies(mediaRootPath);

        //Printing movies data on console
        for (Movie movie : moviesList) {
            System.out.println("\nMovie: " + movie.getName() + ", Year: " + (movie.getYear()!=null ? movie.getYear():"N/A") + ", IMDB Rating: " + movie.getImdbRating());
            System.out.println("Path: " + movie.getPath());
        }

        long stop = System.currentTimeMillis();
        System.out.println( "Elapsed: " + (stop - start) + " ms" );
    }

    /**
     * This is primary method to find movies from your storage media.
     * @param mediaRootPath - Root path of your storage media such as HDD, Pen Drive, Mobile Storage etc.
     * @return List<Movie> - List of Movies
     */
    public static List<Movie> findMovies(String mediaRootPath) {
        Map<String, Movie> movieMap = CacheUtils.load();
        List<Movie> moviesList = DirectoryScanner.getMoviesList(mediaRootPath);
        Movie movieResult = null;

        for (Movie movie : moviesList) {
            System.out.println("Processing: " + movie.getName() + (movie.getYear()!=null?" - "+movie.getYear():""));
            String movieKey = movie.getName() + (movie.getYear()!=null?"_"+movie.getYear():"");

            if(movieMap!=null) { //Movie lookup from local store.
                movieResult = movieMap.get(movieKey);
                if (movieResult!=null && !movieResult.isFoundOnOmdb() && Boolean.parseBoolean(config.get("cache.db.retry.unavailable").toString())) {
                    movieResult = null;
                }
            }

            if (movieResult==null) { //Movie lookup from OMDB Service.
                movieResult = fetchMovieFromOMDBListing(null, movie.getName(),"movie", movie.getYear(), "short", "json", true, null, null);
                if (movieResult==null) { //Movie not found on OMDB.
                    movieResult = new Movie();
                    movieResult.setName(movie.getName());
                    movieResult.setYear(movie.getYear());
                    movieResult.setFoundOnOmdb(false);
                } else { //Movie found on OMDB.
                    movieResult.setFoundOnOmdb(true);
                    // System.out.println("Fetched online: " + movieResult.getName());
                }

                if(movieMap==null) { //In case local store is initialized for the first time.
                    movieMap = new HashMap<>();
                }
            }

            if (movieResult!=null) { //Preparing movie map to save on local store.
                movieResult.setPath(movie.getPath());
                movieMap.put(movieKey, movieResult);
            }
        }

        CacheUtils.save(movieMap); //Saving movie map to local store.

        //Returning sorted movies list by IMDB Rating.
        return sortMoviesByIMDBRating(movieMap);
    }

    /**
     * This method fetches data from OMDB Service.
     * @param imdbId - IMDB Id
     * @param title - Movie Title
     * @param type - Type of title to search. E.g. movie or series
     * @param year - Year of Release
     * @param plot - Plot content size. E.g. short or full
     * @param wsReturnType - Web Service return type. E.g. json or xml
     * @param inclRtTomatoesRating - Include Tomatoes Rating
     * @param jsonpCallbackName - Json callback name
     * @param apiVersion - Api Version
     * @return
     */
    private static Movie fetchMovieFromOMDBListing(final String imdbId, final String title, final String type, final String year, final String plot, final String wsReturnType, final boolean inclRtTomatoesRating, final String jsonpCallbackName, final String apiVersion) {
        Movie movie = null;
        String requestUrl;
        try {

            requestUrl = formRequestURL(imdbId, title, type, year, plot, wsReturnType, inclRtTomatoesRating, jsonpCallbackName, apiVersion);

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            String full = "";

            while ((output = br.readLine()) != null) {
                full += output;
            }

            JSONObject obj = new JSONObject(full);
            movie = convertJsonToMovie(obj);
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movie;
    }

    /**
     * This method forms request URL for OMDB Service.
     * @param imdbId - IMDB Id
     * @param title - Movie Title
     * @param type - Type of title to search. E.g. movie or series
     * @param year - Year of Release
     * @param plot - Plot content size. E.g. short or full
     * @param wsReturnType - Web Service return type. E.g. json or xml
     * @param inclRtTomatoesRating - Include Tomatoes Rating
     * @param jsonpCallbackName - Json callback name
     * @param apiVersion - Api Version
     * @return
     */
    private static String formRequestURL(final String imdbId, final String title, final String type, final String year, final String plot, final String wsReturnType, final boolean inclRtTomatoesRating, final String jsonpCallbackName, final String apiVersion) {
        String requestUrl = OMDB_API_URL+"?";

        if(imdbId!=null) {
            requestUrl += ("i="+imdbId+"&");
        } else if(title!=null) {
            requestUrl += ("t="+title.replaceAll(" " , "\\+")+"&");
        }

        if(type!=null) {
            requestUrl += ("type="+type+"&");
        }

        if(year!=null) {
            requestUrl += ("y="+year+"&");
        }

        if(plot!=null) {
            requestUrl += ("plot="+plot+"&");
        }

        if(wsReturnType!=null) {
            requestUrl += ("r="+wsReturnType+"&");
        }

        requestUrl += ("tomatoes="+inclRtTomatoesRating+"&");

        if(jsonpCallbackName!=null) {
            requestUrl += ("callback="+jsonpCallbackName+"&");
        }

        if(apiVersion!=null) {
            requestUrl += ("v="+apiVersion+"&");
        }

        requestUrl = requestUrl.substring(0,requestUrl.lastIndexOf('&'));

        return requestUrl;
    }

    /**
     * This method converts OMDB Service Response JSON to Movie entity model.
     * @param obj - OMDB Service Response JSON Object
     * @return Movie - The Movie entity
     * @throws JSONException
     */
    private static Movie convertJsonToMovie(JSONObject obj) throws JSONException {
        Movie movie = null;

        if(obj.getString("Response")!=null && obj.getString("Response").equalsIgnoreCase("true")) {
            movie = new Movie();
            movie.setName((obj.has("Title")?obj.getString("Title"):null));
            movie.setYear((obj.has("Year")?obj.getString("Year"):null));
            movie.setRated((obj.has("Rated")?obj.getString("Rated"):null));
            movie.setReleasedOn((obj.has("Released")?obj.getString("Released"):null));
            movie.setDuration((obj.has("Runtime")?obj.getString("Runtime"):null));
            movie.setGenre((obj.has("Genre")?obj.getString("Genre"):null));
            movie.setDirector((obj.has("Director")?obj.getString("Director"):null));
            movie.setWriter((obj.has("Writer")?obj.getString("Writer"):null));
            movie.setActors((obj.has("Actors")?obj.getString("Actors"):null));
            movie.setPlot((obj.has("Plot")?obj.getString("Plot"):null));
            movie.setLanguage((obj.has("Language")?obj.getString("Language"):null));
            movie.setCountry((obj.has("Country")?obj.getString("Country"):null));
            movie.setAwards((obj.has("Awards")?obj.getString("Awards"):null));
            movie.setPosterImgUrl((obj.has("Poster")?obj.getString("Poster"):null));
            movie.setMetascore((obj.has("Metascore")?obj.getString("Metascore"):null));

            if(obj.has("imdbRating")) {
                String imdbRating = obj.getString("imdbRating");
                if(!imdbRating.equalsIgnoreCase("N/A")) {
                    movie.setImdbRating(Double.parseDouble(imdbRating));
                }
            }

            movie.setImdbVotes((obj.has("imdbVotes")?obj.getString("imdbVotes"):null));
            movie.setImdbID((obj.has("imdbID")?obj.getString("imdbID"):null));
            movie.setTomatoMeter((obj.has("tomatoMeter")?obj.getString("tomatoMeter"):null));
            movie.setTomatoImage((obj.has("tomatoImage")?obj.getString("tomatoImage"):null));
            movie.setTomatoRating((obj.has("tomatoRating")?obj.getString("tomatoRating"):null));
            movie.setTomatoReviews((obj.has("tomatoReviews")?obj.getString("tomatoReviews"):null));
            movie.setTomatoFresh((obj.has("tomatoFresh")?obj.getString("tomatoFresh"):null));
            movie.setTomatoRotten((obj.has("tomatoRotten")?obj.getString("tomatoRotten"):null));
            movie.setTomatoConsensus((obj.has("tomatoConsensus")?obj.getString("tomatoConsensus"):null));
            movie.setTomatoUserMeter((obj.has("tomatoUserMeter")?obj.getString("tomatoUserMeter"):null));
            movie.setTomatoUserRating((obj.has("tomatoUserRating")?obj.getString("tomatoUserRating"):null));
            movie.setTomatoUserReviews((obj.has("tomatoUserReviews")?obj.getString("tomatoUserReviews"):null));
            movie.setTomatoURL((obj.has("tomatoURL")?obj.getString("tomatoURL"):null));
            movie.setDvdReleasedOn((obj.has("DVD")?obj.getString("DVD"):null));
            movie.setBoxOfficeCollection((obj.has("BoxOffice")?obj.getString("BoxOffice"):null));
            movie.setProductionHouse((obj.has("Production")?obj.getString("Production"):null));
            movie.setWebsite((obj.has("Website")?obj.getString("Website"):null));
        }

        return movie;
    }

    /**
     * This method sorts movie map and converts into list of movies.
     * @param movieMap - Map containing all the movies data fetched from OMDB Service.
     * @return
     */
    private static List<Movie> sortMoviesByIMDBRating(Map<String,Movie> movieMap) {

        List<Movie> moviesList = new ArrayList<>();
        List<Map.Entry<String, Movie>> entryList = new ArrayList<Map.Entry<String, Movie>>(movieMap.entrySet());
        Collections.sort(
                entryList, new Comparator<Map.Entry<String, Movie>>() {
                    @Override
                    public int compare(Map.Entry<String, Movie> integerEmployeeEntry,
                                       Map.Entry<String, Movie> integerEmployeeEntry2) {
                        return new Double(integerEmployeeEntry2.getValue().getImdbRating())
                                .compareTo(integerEmployeeEntry.getValue().getImdbRating());
                    }
                }
        );

        for (Map.Entry<String, Movie> movieRes : entryList) {
            moviesList.add(movieRes.getValue());
        }

        return moviesList;
    }
}