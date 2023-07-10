package model;

import service.CategoryService;
import service.FilmService;
import service.HelperService;
import service.PlatformService;

import java.util.ArrayList;

public class Application {
    private boolean panelKey = true;
    private boolean adminKey;
    private boolean userKey;
    private ArrayList<Category> categoryList = new ArrayList<>();
    private ArrayList<Platform> platformList = new ArrayList<>();
    private ArrayList<Film> filmList = new ArrayList<>();
    private int menuOption;
    CategoryService categoryService = new CategoryService();
    PlatformService platformService = new PlatformService();
    FilmService filmService = new FilmService();

    public void exitPanel() {
        System.out.println("Çıkış işlemi gerçekleştiriliyor...");
        this.adminKey = false;
        this.panelKey = false;
    }

    public void exitUser() {
        System.out.println("Çıkış işlemi gerçekleştiriliyor...");
        this.userKey = false;
        this.panelKey = false;
    }

    public void adminUpperMenu() {
        this.adminKey = false;
    }

    public void userUpperMenu() {
        this.userKey = false;
    }

    public void manageAdminPanel() {
        this.adminKey = true;
        System.out.println("Admin paneline giriş yaptınız. Yapmak istediğiniz işlemi seçiniz!");
        HelperService.writeDivider();
        while (this.adminKey) {
            System.out.println("\nYapılabilecek işlemler:\n1-KATEGORİ EKLE\n2-PLATFORM EKLE\n3-FİLM EKLE\n9-ÜST MENÜ\n0-ÇIKIŞ\n");
            int adminOption = HelperService.getIntInput("Seçiminiz: ");
            switch (adminOption) {
                case 1 -> categoryService.addCategory(this.categoryList);
                case 2 -> platformService.addPlatform(this.platformList);
                case 3 -> filmService.addFilm(this.categoryList, this.platformList, this.filmList);
                case 9 -> adminUpperMenu();
                case 0 -> exitPanel();
                default -> HelperService.wrongValue();
            }
        }
    }

    public void manageUserPanel() {
        System.out.println("Kullanıcı paneline giriş yaptınız. Yapmak istediğiniz işlemi seçiniz!");
        HelperService.writeDivider();
        this.userKey = true;
        while (this.userKey) {
            System.out.println("\n1-KATEGORİLERİ GÖRÜNTÜLE\n2-PLATFORMLARI GÖRÜNTÜLE\n3-FİLMLERİ GÖRÜNTÜLE\n9-ÜST MENÜYE DÖN\n0-ÇIKIŞ\n");
            int userOption = HelperService.getIntInput("Seçiminiz: ");
            switch (userOption) {
                case 1 -> categoryService.showCategories(this.categoryList);
                case 2 -> platformService.showPlatforms(this.platformList);
                // show with filtering and counts
                case 3 -> filmService.showFilms(categoryList, filmList);
                // menu management
                case 9 -> userUpperMenu();
                case 0 -> exitUser();
                default -> HelperService.wrongValue();
            }
        }
    }

    public void run() {
        while (this.panelKey) {
            HelperService.writePanelOption();
            this.menuOption = HelperService.getIntInput("Seçiminiz: ");
            switch (this.menuOption) {
                // admin processes
                case 1 -> manageAdminPanel();
                // user processes
                case 2 -> manageUserPanel();
                // panel control
                case 0 -> exitPanel();
                default -> HelperService.wrongValue();
            }
        }
    }
}