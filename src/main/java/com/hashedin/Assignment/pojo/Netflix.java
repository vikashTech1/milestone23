package com.hashedin.Assignment.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Netflix {

    @Id
    private String showId;
    private String type;
    private String title;
    private String director;
    private String cast;
    private String country;
    private String dateAdded;
    private String releaseYear;
    private String rating;
    private String duration;
    private String listedAs;
    private String description;

    public Netflix() {
    }

    public Netflix(String showId, String type, String title, String director, String cast, String country, String dateAdded, String releaseYear, String rating, String duration, String listedAs, String description) {
        this.showId = showId;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.dateAdded = dateAdded;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.duration = duration;
        this.listedAs = listedAs;
        this.description = description;
    }

    public String getShowId() {
        return showId;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }

    public String getCountry() {
        return country;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public String getDuration() {
        return duration;
    }

    public String getListedAs() {
        return listedAs;
    }

    public String getDescription() {
        return description;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setListedAs(String listedAs) {
        this.listedAs = listedAs;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Netflix{" +
                "showId='" + showId + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", cast='" + cast + '\'' +
                ", country='" + country + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                ", listedAs='" + listedAs + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
