package service;

import model.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlatformService {

    public void addPlatform(ArrayList<Platform> platformList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Platform ismi giriniz: ");
        String platformName = scanner.next();
        Platform platform = new Platform(platformName);
        platformList.add(platform);
    }

    public void showPlatforms(List<Platform> platformList) {
        if (platformList.size() <= 0) {
            System.out.println("Kayıtlı platform bulunamamıştır!");
        } else {
            HelperService.writeDivider();
            System.out.println("--Mevcut Platformlar--");
            for (int i = 0; i < platformList.size(); i++) {
                System.out.println(i + 1 + "-" + platformList.get(i));
            }
            HelperService.writeDivider();
        }
    }
}
