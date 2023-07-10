package service;

import model.Category;
import java.util.List;
import java.util.Scanner;

public class CategoryService {

    public void addCategory(List<Category> categoryList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Kategori ismi giriniz:");
        String categoryName = scanner.next();
        Category category = new Category(categoryName);
        categoryList.add(category);
    }

    public void showCategories(List<Category> categoryList) {
        if (categoryList.size() == 0) {
            System.out.println("Kayıtlı kategori bulunamamıştır!");
        } else {
            HelperService.writeDivider();
            System.out.println("--Mevcut Kategoriler--");
            for (int i = 0; i < categoryList.size(); i++) {
                System.out.println(i + 1 + "-" + categoryList.get(i));
            }
            HelperService.writeDivider();
        }
    }
}


