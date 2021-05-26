package ru.credit.calculator.console.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


//Проверка и создание каталогов
public class CheckPath {

    public static List<String> checkPath(String commonPath) {
        List<String> pathArray = new ArrayList<>();
        pathArray.add(checkCatalog(commonPath + "/parameters"));
        pathArray.add(checkCatalog(commonPath + "/result"));
        System.out.println("Каталог для параметров: " + pathArray.get(0));
        System.out.println("Каталог для результатов: " + pathArray.get(1));
        return pathArray;
    }


    private static String checkCatalog(String path) {
        Path pathParam = Paths.get(path);
        if (!Files.exists(pathParam)) {
            try {
                Files.createDirectories(pathParam);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

}
