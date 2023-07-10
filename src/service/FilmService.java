package service;

import model.Category;
import model.Film;
import model.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmService {
    CategoryService categoryService = new CategoryService();

    public ArrayList<Film> filterFilmByCategory(Category category, List<Film> filmList) {
        ArrayList<Film> filteredFilms = new ArrayList<>();
        for (Film f : filmList) {
            if (f.getCategoryArray().contains(category)) {
                filteredFilms.add(f);
            }
        }
        return filteredFilms;
    }

    public void showFilms(List<Category> categoryList, List<Film> filmList) {
        ArrayList<Film> filmToShow;
        System.out.println("Lütfen bir kategori seçiniz.");
        categoryService.showCategories(categoryList);

        int selectedCategoryNum = HelperService.getIntInput("Kategori seçiminiz: ");
        selectedCategoryNum--;
        Category selectedCategory = categoryList.get(selectedCategoryNum);
        filmToShow = filterFilmByCategory(selectedCategory, filmList);

        if (filmToShow.size() == 0) {
            System.out.println("\n" + selectedCategory + "kategorisine ait kayıtlı film bulunamamıştır!");
        } else {
            HelperService.writeDivider();
            System.out.println(selectedCategory + " içerisinde bulunan film sayısı: " + selectedCategory.getFilmCount() + "\n");
            for (Film f : filmToShow) {
                System.out.println(f);
            }
            HelperService.writeDivider();
        }
    }

    public boolean checkAddFilmConditionsProvided(List<Category> categoryList, List<Platform> platformList) {
        if (categoryList.size() == 0) {
            System.out.println("Kategori listesi boş, lütfen önce kategori ekleyiniz.");
            return false;
        }
        if (platformList.size() == 0) {
            System.out.println("Platform listesi boş, lütfen önce platform ekleyiniz.");
            return false;
        }
        return true;
    }

    public void addFilm(List<Category> categoryList, List<Platform> platformList, List<Film> filmList) {
        boolean addFilmKey = true;
        while (addFilmKey) {
            if (!checkAddFilmConditionsProvided(categoryList, platformList)) {
                break;
            }
            Scanner scanner = new Scanner(System.in);

            System.out.print("Film adı: ");
            String filmName = scanner.next();
            filmName += scanner.nextLine();

            int releaseYear = HelperService.getIntInput("Çıkış yılı: ");

            System.out.print("Yönetmen adı: ");
            String directorName = scanner.next();
            directorName += scanner.nextLine();

            double imdbScore = HelperService.getDoubleInput("IMDB puanı: ");

            int filmTime = HelperService.getIntInput("Dakika cinsinden filmin süresi: ");

            System.out.print("Seans bilgisi: ");
            String sessionInfo = scanner.next();
            sessionInfo += scanner.nextLine();

            // making choice from inserted categories with selection
            boolean filmCategoryKey = true;
            ArrayList<Category> categoryListAdd = new ArrayList<>();
            System.out.println("Filmin hangi kategorilerde olduğunu seçiniz:");
            while (filmCategoryKey) {
                // print inserted films
                for (int i = 0; i < categoryList.size(); i++) {
                    System.out.println(i + 1 + "-" + categoryList.get(i));
                }
                System.out.println("0-Kaydet ve devam et");
                // make choice
                int categoryOption = HelperService.getIntInput("Kategori seçiminiz: ");
                if (categoryOption > 0 && categoryOption <= categoryList.size()) {
                    categoryListAdd.add(categoryList.get(categoryOption - 1));
                    categoryList.get(categoryOption - 1).increaseFilmCount();
                    System.out.println(categoryList.get(categoryOption - 1) + " Eklendi");
                } else if (categoryOption == 0) {
                    filmCategoryKey = false;
                } else {
                    System.out.println("Hatalı bir değer girildi! Tekrar deneyiniz!");
                }
            }
            // making choice from inserted platforms with selection
            boolean filmPlatformKey = true;
            ArrayList<Platform> platformListAdd = new ArrayList<>();
            System.out.println("Filmin hangi platformlarda yayınlandığını seçiniz:");
            while (filmPlatformKey) {
                // print inserted platforms
                for (int i = 0; i < platformList.size(); i++) {
                    System.out.println(i + 1 + "-" + platformList.get(i));
                }
                System.out.println("0-Kaydet ve devam et");
                // make choice
                int platformOption = HelperService.getIntInput("Platform seçiminiz: ");
                if (platformOption > 0 && platformOption <= platformList.size()) {
                    platformListAdd.add(platformList.get(platformOption - 1));
                    System.out.println(platformList.get(platformOption - 1) + " Eklendi");
                } else if (platformOption == 0) {
                    filmPlatformKey = false;
                } else {
                    System.out.println("Hatalı bir değer girildi! Tekrar deneyiniz!");
                }
            }
            Film addFilm = new Film(filmName, releaseYear, directorName, imdbScore, categoryListAdd, filmTime, platformListAdd, sessionInfo);
            filmList.add(addFilm);
            addFilmKey = false;
        }
    }
}