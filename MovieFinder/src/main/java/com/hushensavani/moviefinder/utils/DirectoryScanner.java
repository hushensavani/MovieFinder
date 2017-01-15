package com.hushensavani.moviefinder.utils;

import com.hushensavani.moviefinder.model.Movie;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created on: 14-January-2017
 *
 * @author <a href="mailto:husen.savani1@gmail.com">Hushen Savani</a>
 *
 * @description This utility is responsible for scanning media such as HDD, Pen Drive, Mobile Storage etc for movies.
 *
 */
public class DirectoryScanner {

    private static long MV_MIN_FILE_SIZE = 300000000;
    private static String MV_FILE_EXT_PATTERN = "glob:**.{webm,mkv,flv,vob,ogv,ogg,drc,gif,gifv,mng,avi,mov,qt,wmv,yuv,rm,rmvb,asf,amv,mp4,m4p,m4v,mp2,mpe,mpv,mpg,mpeg,m2v,m4v,svi,3gp,3g2,mxf,roq,nsv,f4v,f4p,f4a,f4b}";

    /**
     * This method scans the root directory of the media and fetches all the files which are movies.
     * @param filePath - Root path of your storage media such as HDD, Pen Drive, Mobile Storage etc.
     * @return List<Movie> - List of movies objects with minimal information from movies file listing on storage media.
     */
    public static List<Movie> getMoviesList(String filePath) {
        List<Movie> moviesList = new ArrayList<>();
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(MV_FILE_EXT_PATTERN);
        Path path= Paths.get(filePath);

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(!attrs.isDirectory()){
                        if (matcher.matches(file) && attrs.size()>MV_MIN_FILE_SIZE) {
                            Movie movie = NamingUtils.getMovieFromName(file.getFileName().toString());
                            movie.setPath(file.toAbsolutePath().toString());
                            moviesList.add(movie);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    /*public static void main(String[] args) throws Exception{
        Hashtable config = ApplicationConfigUtil.getInstance().getConfigHT("config");
        String mediaRootPath = (String) config.get("media.root.path");
        List<Movie> moviesList = getMoviesList(mediaRootPath);
        for (Movie movie : moviesList) {
            System.out.println(movie.getName() + (movie.getYear()!=null?" : " + movie.getYear():""));
        }
    }*/
}