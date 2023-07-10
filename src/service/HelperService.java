package service;

import java.util.Scanner;

public class HelperService {
    public static void writeDivider() {
        System.out.println("****************************************************************************");
    }

    public static void wrongValue() {
        System.out.println("Hatalı bir değer girildi! Tekrar deneyiniz!");
    }

    public static int getIntInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Lütfen bir sayı giriniz!");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextInt();
    }

    public static double getDoubleInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.print("Lütfen ondalıklı sayı giriniz!");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextDouble();
    }

    public static void writePanelOption() {
        System.out.println("1- ADMİN PANELİ");
        System.out.println("2- KULLANICI PANELİ");
        System.out.println("0- ÇIKIŞ");
    }
}
