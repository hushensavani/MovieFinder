package com.hushensavani.moviefinder.utils;

import com.hushensavani.moviefinder.model.Movie;

import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created on: 15-January-2017
 *
 * @author <a href="mailto:husen.savani1@gmail.com">Hushen Savani</a>
 *
 * @description This utility is responsible for caching search results in local store for local lookup.
 *
 */
public class CacheUtils {

    private static String cacheDBPath;

    static {
        Hashtable config = ApplicationConfigUtil.getInstance().getConfigHT("config");
        cacheDBPath = (String) config.get("cache.db.path");
    }

    /**
     * This method loads the local store.
     * @return Map<String, Movie> - Map containg movies data saved on local store.
     */
    public static Map<String, Movie> load() {

        Map<String, Movie> movieMap = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheDBPath))) {
           movieMap = (Map<String, Movie>)ois.readObject();
        } catch (Exception e) {
            System.out.println("No local store is found at the specified location. Seems you are running the program for the first time!");
        }

        return movieMap;
    }

    /**
     * This method saves the movie data map in local store.
     * @param movieMap
     */
    public static void save(Map<String, Movie> movieMap) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cacheDBPath))) {
            oos.writeObject(movieMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*public static void main(String[] args) {
        Map<String, Movie> movieMap = new HashMap<>();

        Movie movie = new Movie();
        movie.setName("Dangal");
        movie.setYear("2016");
        movieMap.put(movie.getName(), movie);

        movie = new Movie();
        movie.setName("Piku");
        movie.setYear("2015");
        movieMap.put(movie.getName(), movie);

        movie = new Movie();
        movie.setName("Ae Dil Hai Mushkil");
        movie.setYear("2016");
        movieMap.put(movie.getName(), movie);

        save(movieMap);
        System.out.println(load());

    }*/
}
