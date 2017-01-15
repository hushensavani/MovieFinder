package com.hushensavani.moviefinder.model;

import java.io.Serializable;

/**
 * Created on: 14-January-2017
 *
 * @author <a href="mailto:husen.savani1@gmail.com">Hushen Savani</a>
 *
 * @description This is a model class of Movie entity.
 *
 */
public class Movie implements Serializable {
    private String name;
    private String year;
    private String rated;
    private String releasedOn;
    private String duration;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String posterImgUrl;
    private String metascore;
    private double imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String tomatoMeter;
    private String tomatoImage;
    private String tomatoRating;
    private String tomatoReviews;
    private String tomatoFresh;
    private String tomatoRotten;
    private String tomatoConsensus;
    private String tomatoUserMeter;
    private String tomatoUserRating;
    private String tomatoUserReviews;
    private String tomatoURL;
    private String dvdReleasedOn;
    private String boxOfficeCollection;
    private String productionHouse;
    private String website;
    private String path;
    private boolean isFoundOnOmdb;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(String releasedOn) {
        this.releasedOn = releasedOn;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPosterImgUrl() {
        return posterImgUrl;
    }

    public void setPosterImgUrl(String posterImgUrl) {
        this.posterImgUrl = posterImgUrl;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTomatoMeter() {
        return tomatoMeter;
    }

    public void setTomatoMeter(String tomatoMeter) {
        this.tomatoMeter = tomatoMeter;
    }

    public String getTomatoImage() {
        return tomatoImage;
    }

    public void setTomatoImage(String tomatoImage) {
        this.tomatoImage = tomatoImage;
    }

    public String getTomatoRating() {
        return tomatoRating;
    }

    public void setTomatoRating(String tomatoRating) {
        this.tomatoRating = tomatoRating;
    }

    public String getTomatoReviews() {
        return tomatoReviews;
    }

    public void setTomatoReviews(String tomatoReviews) {
        this.tomatoReviews = tomatoReviews;
    }

    public String getTomatoFresh() {
        return tomatoFresh;
    }

    public void setTomatoFresh(String tomatoFresh) {
        this.tomatoFresh = tomatoFresh;
    }

    public String getTomatoRotten() {
        return tomatoRotten;
    }

    public void setTomatoRotten(String tomatoRotten) {
        this.tomatoRotten = tomatoRotten;
    }

    public String getTomatoConsensus() {
        return tomatoConsensus;
    }

    public void setTomatoConsensus(String tomatoConsensus) {
        this.tomatoConsensus = tomatoConsensus;
    }

    public String getTomatoUserMeter() {
        return tomatoUserMeter;
    }

    public void setTomatoUserMeter(String tomatoUserMeter) {
        this.tomatoUserMeter = tomatoUserMeter;
    }

    public String getTomatoUserRating() {
        return tomatoUserRating;
    }

    public void setTomatoUserRating(String tomatoUserRating) {
        this.tomatoUserRating = tomatoUserRating;
    }

    public String getTomatoUserReviews() {
        return tomatoUserReviews;
    }

    public void setTomatoUserReviews(String tomatoUserReviews) {
        this.tomatoUserReviews = tomatoUserReviews;
    }

    public String getTomatoURL() {
        return tomatoURL;
    }

    public void setTomatoURL(String tomatoURL) {
        this.tomatoURL = tomatoURL;
    }

    public String getDvdReleasedOn() {
        return dvdReleasedOn;
    }

    public void setDvdReleasedOn(String dvdReleasedOn) {
        this.dvdReleasedOn = dvdReleasedOn;
    }

    public String getBoxOfficeCollection() {
        return boxOfficeCollection;
    }

    public void setBoxOfficeCollection(String boxOfficeCollection) {
        this.boxOfficeCollection = boxOfficeCollection;
    }

    public String getProductionHouse() {
        return productionHouse;
    }

    public void setProductionHouse(String productionHouse) {
        this.productionHouse = productionHouse;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFoundOnOmdb() {
        return isFoundOnOmdb;
    }

    public void setFoundOnOmdb(boolean foundOnOmdb) {
        isFoundOnOmdb = foundOnOmdb;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", rated='" + rated + '\'' +
                ", releasedOn='" + releasedOn + '\'' +
                ", duration='" + duration + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", awards='" + awards + '\'' +
                ", posterImgUrl='" + posterImgUrl + '\'' +
                ", metascore='" + metascore + '\'' +
                ", imdbRating=" + imdbRating +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", tomatoMeter='" + tomatoMeter + '\'' +
                ", tomatoImage='" + tomatoImage + '\'' +
                ", tomatoRating='" + tomatoRating + '\'' +
                ", tomatoReviews='" + tomatoReviews + '\'' +
                ", tomatoFresh='" + tomatoFresh + '\'' +
                ", tomatoRotten='" + tomatoRotten + '\'' +
                ", tomatoConsensus='" + tomatoConsensus + '\'' +
                ", tomatoUserMeter='" + tomatoUserMeter + '\'' +
                ", tomatoUserRating='" + tomatoUserRating + '\'' +
                ", tomatoUserReviews='" + tomatoUserReviews + '\'' +
                ", tomatoURL='" + tomatoURL + '\'' +
                ", dvdReleasedOn='" + dvdReleasedOn + '\'' +
                ", boxOfficeCollection='" + boxOfficeCollection + '\'' +
                ", productionHouse='" + productionHouse + '\'' +
                ", website='" + website + '\'' +
                ", path='" + path + '\'' +
                ", isFoundOnOmdb=" + isFoundOnOmdb +
                '}';
    }
/*
    @Override
    public int compareTo(Object o) {
        return (int)(imdbRating - ((Movie)o).imdbRating);
    }*/
}