package model;

public class Platform {
    private String name;
    private int filmCount;

    public Platform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFilmCount() {
        return filmCount;
    }

    public void setFilmCount(int filmCount) {
        this.filmCount = filmCount;
    }

    @Override
    public String toString() {
        return "Platform adı = '" + name + '\'';
    }
}
