package model;

import java.util.Arrays;
import java.util.List;

public class Film {
    private String filmName;
    private int year;
    private String director;
    private double imdb;
    private List<Category> categoryArray;
    private int time;
    private List<Platform> platformList;
    private String session;

    public Film(String filmName, int year, String director, double imdb, List<Category> categoryArray, int time, List<Platform> platformList, String session) {
        this.filmName = filmName;
        this.year = year;
        this.director = director;
        this.imdb = imdb;
        this.categoryArray = categoryArray;
        this.time = time;
        this.platformList = platformList;
        this.session = session;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getImdb() {
        return imdb;
    }

    public void setImdb(double imdb) {
        this.imdb = imdb;
    }

    public List<Category> getCategoryArray() {
        return categoryArray;
    }

    public void setCategoryArray(List<Category> categoryArray) {
        this.categoryArray = categoryArray;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Platform> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(List<Platform> platformList) {
        this.platformList = platformList;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Film adı = '" + filmName + '\'' +
                ", Çıktığı yıl = " + year +
                ", Yönetmen = '" + director + '\'' +
                ", IMDB puanı = " + imdb +
                ", Ait olduğu kategoriler = " + categoryArray +
                ", Süresi = " + time +
                ", Bulunduğu platformlar = " + platformList +
                ", Gösterim seansları = '" + session + '\'' +
                '}';
    }
}
