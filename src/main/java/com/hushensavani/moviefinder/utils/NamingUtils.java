package com.hushensavani.moviefinder.utils;

import com.hushensavani.moviefinder.model.Movie;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on: 14-January-2017
 *
 * @author <a href="mailto:husen.savani1@gmail.com">Hushen Savani</a>
 *
 * @description This utility is responsible for creating searchable movie name from the movie file.
 *
 */
public class NamingUtils {

    private static final String YEAR_REG_EX = "17\\d{2}|18\\d{2}|19\\d{2}|20\\d{2}";
    private static final String PXL_REG_EX = "480p|720p|1080p";
    private static final String RIP_REG_EX = "BRRip|BRip|DVDRip|dvdscr|1cd|2cd|cd1|cd2|DVDivX|CAMRip|XVid|BDRip|DVDSrc|WebRip|HDRip|CDRip|pDvD|x264|Blu-ray|BluRay|HDTV|Hindi|WorldFree4u";
    private static final int MAX_VID_EXT_LEN = 4;

    /**
     * This method generates an organic and searchable movie title from movie file name on storage media.
     * @param movieName - Movie file name on storage media.
     * @return Movie - Movie object with minimal information (E.g. Searchable Movie Title and Year) from the movie file name on storage media.
     */
    public static Movie getMovieFromName(final String movieName) {
        String correctedMovieName;
        Movie movie = null;
        Matcher matcher;

        if (movieName!=null) {
            //System.out.println("Processing: " + movieName);
            movie = new Movie();

            if(movieName.contains(".") && (movieName.length()-movieName.lastIndexOf('.')-1)<=MAX_VID_EXT_LEN) {
                correctedMovieName = movieName.substring(0,movieName.lastIndexOf('.'));
            } else {
                correctedMovieName = movieName;
            }

            matcher = Pattern.compile(YEAR_REG_EX).matcher(correctedMovieName);

            if (matcher.find()) {
                correctedMovieName = correctedMovieName.substring(0,matcher.start());
                movie.setYear(matcher.group());
            }

            matcher = Pattern.compile(PXL_REG_EX, Pattern.CASE_INSENSITIVE).matcher(correctedMovieName);

            if (matcher.find()) {
                correctedMovieName = correctedMovieName.substring(0,matcher.start());
            }

            matcher = Pattern.compile(RIP_REG_EX, Pattern.CASE_INSENSITIVE).matcher(correctedMovieName);

            if (matcher.find()) {
                correctedMovieName = correctedMovieName.substring(0,matcher.start());
            }

            if(correctedMovieName.trim().startsWith("[")) {
                correctedMovieName = correctedMovieName.substring(correctedMovieName.indexOf("]")+1,correctedMovieName.length());
            }

            correctedMovieName = correctedMovieName.replaceAll("\\."," ").replaceAll("[()]","").replaceAll("-"," ").replaceAll("_"," ").replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\\{","").replaceAll("\\}","");

            movie.setName(correctedMovieName.trim().replaceAll(" +", " "));
        }

        return movie;
    }

    /*public static void main(String[] args) {
        String movieName = " [ www.torrents.com ] - The.Day.The.Earth.Stood.Still-720p.2008.BrRip.Dual.audio.(English-Hindi).{Khiladi786}.webm";
        Movie movie = getMovieFromName(movieName);
        System.out.println(movie.getName());
        System.out.println(movie.getYear());
    }*/
}