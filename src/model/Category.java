package model;

public class Category {
    private String name;
    private int filmCount=0;

    public Category(String name) {
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

    public void increaseFilmCount(){
        this.filmCount++;
    }

    @Override
    public String toString() {
        return "Kategori adı = '" + name + '\'';
    }
}
