package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private boolean panelKey = true;
    private boolean adminKey;
    private boolean userKey;
    private ArrayList<Category> categoryList = new ArrayList<>();
    private ArrayList<Platform> platformList = new ArrayList<>();
    private ArrayList<Film> filmList = new ArrayList<>();
    private Category category;
    private Platform platform;
    private Film film;
    private int menuOption;
    Scanner scanner = new Scanner(System.in);

    public int getIntInput(String message) {
        System.out.print(message);
        while (!this.scanner.hasNextInt()) {
            System.out.println("Lütfen bir sayı giriniz!");
            scanner.next();
            System.out.print(message);
        }
        return this.scanner.nextInt();
    }

    public double getDoubleInput(String message) {
        System.out.print(message);
        while (!this.scanner.hasNextDouble()) {
            System.out.print("Lütfen ondalıklı sayı giriniz!");
            this.scanner.next();
            System.out.print(message);
        }
        return this.scanner.nextDouble();
    }

    public void writePanelOption() {
        System.out.println("1- ADMİN PANELİ");
        System.out.println("2- KULLANICI PANELİ");
        System.out.println("0- ÇIKIŞ");
    }

    public void addCategory() {
        System.out.print("Kategori ismi giriniz:");
        String categoryName = scanner.next();
        category = new Category(categoryName);
        this.categoryList.add(category);
    }

    public void addPlatform() {
        System.out.print("Platform ismi giriniz: ");
        String platformName = scanner.next();
        platform = new Platform(platformName);
        this.platformList.add(platform);
    }

    public boolean checkAddFilmConditionsProvided() {
        if (this.categoryList.size() == 0) {
            System.out.println("Kategori listesi boş, lütfen önce kategori ekleyiniz.");
            return false;
        }
        if (this.platformList.size() == 0) {
            System.out.println("Platform listesi boş, lütfen önce platform ekleyiniz.");
            return false;
        }
        return true;
    }

    public void addFilm() {
        boolean addFilmKey = true;
        while (addFilmKey) {
            if (!checkAddFilmConditionsProvided()) {
                break;
            }

            System.out.print("Film adı: ");
            String filmName = this.scanner.next();
            filmName += this.scanner.nextLine();

            int releaseYear = getIntInput("Çıkış yılı: ");

            System.out.print("Yönetmen adı: ");
            String directorName = this.scanner.next();
            directorName += this.scanner.nextLine();

            double imdbScore = getDoubleInput("IMDB puanı: ");

            int filmTime = getIntInput("Dakika cinsinden filmin süresi: ");

            System.out.print("Seans bilgisi: ");
            String sessionInfo = this.scanner.next();
            sessionInfo += this.scanner.nextLine();

            // making choice from inserted categories with selection
            boolean filmCategoryKey = true;
            ArrayList<Category> categoryListAdd = new ArrayList<>();
            System.out.println("Filmin hangi kategorilerde olduğunu seçiniz:");
            while (filmCategoryKey) {
                // print inserted films
                for (int i = 0; i < this.categoryList.size(); i++) {
                    System.out.println(i + 1 + "-" + this.categoryList.get(i));
                }
                System.out.println("0-Kaydet ve devam et");
                // make choice
                int categoryOption = getIntInput("Kategori seçiminiz: ");
                if (categoryOption > 0 && categoryOption <= this.categoryList.size()) {
                    categoryListAdd.add(this.categoryList.get(categoryOption - 1));
                    this.categoryList.get(categoryOption - 1).increaseFilmCount();
                    System.out.println(this.categoryList.get(categoryOption - 1) + " Eklendi");
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
                for (int i = 0; i < this.platformList.size(); i++) {
                    System.out.println(i + 1 + "-" + this.platformList.get(i));
                }
                System.out.println("0-Kaydet ve devam et");
                // make choice
                int platformOption = getIntInput("Platform seçiminiz: ");
                if (platformOption > 0 && platformOption <= this.platformList.size()) {
                    platformListAdd.add(this.platformList.get(platformOption - 1));
                    System.out.println(this.platformList.get(platformOption - 1) + " Eklendi");
                } else if (platformOption == 0) {
                    filmPlatformKey = false;
                } else {
                    System.out.println("Hatalı bir değer girildi! Tekrar deneyiniz!");
                }
            }
            Film addFilm = new Film(filmName, releaseYear, directorName, imdbScore, categoryListAdd, filmTime, platformListAdd, sessionInfo);
            this.filmList.add(addFilm);
            addFilmKey = false;
        }
    }

    public void wrongValue() {
        System.out.println("Hatalı bir değer girildi! Tekrar deneyiniz!");
    }

    public void exitPanel() {
        System.out.println("Çıkış işlemi gerçekleştiriliyor...");
        this.adminKey = false;
        this.panelKey = false;
    }

    public void adminUpperMenu() {
        this.adminKey = false;
    }
    public void writeDivider(){
        System.out.println("****************************************************************************");
    }

    public void showCategories() {
        if (this.categoryList.size() == 0) {
            System.out.println("Kayıtlı kategori bulunamamıştır!");
        } else {
            writeDivider();
            System.out.println("--Mevcut Kategoriler--");
            for (int i = 0; i < this.categoryList.size(); i++) {
                System.out.println(i + 1 + "-" + this.categoryList.get(i));
            }
            writeDivider();
        }
    }

    public void showPlatforms() {
        if (this.platformList.size() <= 0) {
            System.out.println("Kayıtlı platform bulunamamıştır!");
        } else {
            writeDivider();
            System.out.println("--Mevcut Platformlar--");
            for (int i = 0; i < this.platformList.size(); i++) {
                System.out.println(i + 1 + "-" + this.platformList.get(i));
            }
            writeDivider();
        }
    }

    public void showFilms() {
        ArrayList<Film> filmToShow;
        System.out.println("Lütfen bir kategori seçiniz.");
        showCategories();

        int selectedCategoryNum = getIntInput("Kategori seçiminiz: ");
        selectedCategoryNum--;
        Category selectedCategory = this.categoryList.get(selectedCategoryNum);
        filmToShow = filterFilmByCategory(selectedCategory);

        if (filmToShow.size() == 0) {
            System.out.println("\n" + selectedCategory + "kategorisine ait kayıtlı film bulunamamıştır!");
        } else {
            writeDivider();
            System.out.println(selectedCategory + " içerisinde bulunan film sayısı: " + selectedCategory.getFilmCount() +"\n");
            for (Film f : filmToShow) {
                System.out.println(f);
            }
            writeDivider();
        }

    }

    public ArrayList<Film> filterFilmByCategory(Category category) {
        ArrayList<Film> filteredFilms = new ArrayList<>();
        for (Film f : this.filmList) {
            if (f.getCategoryArray().contains(category)) {
                filteredFilms.add(f);
            }
        }
        return filteredFilms;
    }

    public void userUpperMenu() {
        this.userKey = false;
    }

    public void exitUser() {
        System.out.println("Çıkış işlemi gerçekleştiriliyor...");
        this.userKey = false;
        this.panelKey = false;
    }

    public void manageAdminPanel() {
        this.adminKey = true;
        System.out.println("Admin paneline giriş yaptınız. Yapmak istediğiniz işlemi seçiniz!");
        writeDivider();
        while (this.adminKey) {
            System.out.println("\nYapılabilecek işlemler:\n1-KATEGORİ EKLE\n2-PLATFORM EKLE\n3-FİLM EKLE\n9-ÜST MENÜ\n0-ÇIKIŞ\n");
            int adminOption = getIntInput("Seçiminiz: ");
            switch (adminOption) {
                case 1 -> addCategory();
                case 2 -> addPlatform();
                case 3 -> addFilm();
                case 9 -> adminUpperMenu();
                case 0 -> exitPanel();
                default -> wrongValue();
            }
        }
    }

    public void manageUserPanel() {
        System.out.println("Kullanıcı paneline giriş yaptınız. Yapmak istediğiniz işlemi seçiniz!");
        writeDivider();
        this.userKey = true;
        while (this.userKey) {
            System.out.println("\n1-KATEGORİLERİ GÖRÜNTÜLE\n2-PLATFORMLARI GÖRÜNTÜLE\n3-FİLMLERİ GÖRÜNTÜLE\n9-ÜST MENÜYE DÖN\n0-ÇIKIŞ\n");
            int userOption = getIntInput("Seçiminiz: ");
            switch (userOption) {
                case 1 -> showCategories();
                case 2 -> showPlatforms();
                // show with filtering and counts
                case 3 -> showFilms();
                // menu management
                case 9 -> userUpperMenu();
                case 0 -> exitUser();
                default -> wrongValue();
            }
        }
    }

    public void run() {
        while (this.panelKey) {
            writePanelOption();
            this.menuOption = getIntInput("Seçiminiz: ");
            switch (this.menuOption) {
                // admin processes
                case 1 -> manageAdminPanel();
                // user processes
                case 2 -> manageUserPanel();
                // panel control
                case 0 -> exitPanel();
                default -> wrongValue();
            }
        }
    }
}