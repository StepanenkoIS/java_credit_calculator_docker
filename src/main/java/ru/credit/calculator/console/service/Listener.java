package ru.credit.calculator.console.service;

import ru.credit.calculator.console.model.FileList;
import ru.credit.calculator.library.model.InitialParameters;
import ru.credit.calculator.library.service.ParsingXML;


import javax.xml.bind.JAXBException;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static ru.credit.calculator.console.service.Logs.addLogs;


public class Listener {

    private static boolean flagRunning = true;

    private static List<FileList> historyFiles = new ArrayList<>();

    //Метод изменния флага (для завершения программы)
    public static void running() {
        flagRunning = false;
    }

    //Запуск слушателя
    public static void runListener(String pathParam, String pathResult) throws InterruptedException, JAXBException, IOException {
        do {
            List<FileList> files = getFilesParam(pathParam);
            List<FileList> listen = checkFiles(files);
            if (listen == null || listen.isEmpty()) {
                Thread.sleep(100);
            } else {
                for (FileList file : listen) {
                    if (!flagRunning) {return;}
                    Listener.createFileCredit(file.getFile(), pathResult);
                }
            }
        } while (flagRunning);
    }

    //Метод считывает все файлы в папке и возвращает в виде Списка
    public static List<FileList> getFilesParam(String pathParam) {
        List<FileList> fileLists = new ArrayList<>();
        File[] files = new File(pathParam).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });

        if (files != null) {
            for (File file : files) {
                FileList fileList = new FileList(file);
                fileLists.add(fileList);
            }
            return fileLists;
        } else {
            return null;
        }
    }

    /*
  Метод проверяет полученный список, все новые файлы добавляются в List<FileList> historyFiles,
  и возвращаются в виде списка List<FileList>.
  */
    private static List<FileList> checkFiles(List<FileList> files) {
        if (files == null) {
            return null;
        }

        if (historyFiles.isEmpty()) {
            historyFiles.addAll(files);
            return files;
        }

        List<FileList> listen = new ArrayList<>();
        for (FileList file : files) {
            if (!historyFiles.contains(file)) {
                listen.add(file);
            }
        }
        historyFiles.addAll(listen);
        return listen;
    }

    // Выполняется парсин параметров и создание файла "Платежи по кредиту"
    public static void createFileCredit(File fileParam, String pathResult) throws JAXBException, IOException {
        InitialParameters parameter = ParsingXML.unmarshalXML(fileParam.getPath());

        addLogs(LocalTime.now() + ": Обработка файла параметров. " + fileParam.getAbsolutePath());
        if (checkParameters(parameter)) {
            addLogs(LocalTime.now() + ": Некорректные данные. " + fileParam.getAbsolutePath());
            return;
        }

        File fileResult = new File(String.format((pathResult + "/credit-%s"), fileParam.getName()));
        if (fileResult.createNewFile()) {
            addLogs(LocalTime.now() + ": Создан новый файл. " + fileResult.getAbsolutePath());
        } else {
            addLogs(LocalTime.now() + ": Изменен существующий файл. " + fileResult.getAbsolutePath());
        }
        ParsingXML.marshalXML(parameter, fileResult.getCanonicalPath());
    }

    //Проверка параметров на ошибки
    public static boolean checkParameters(InitialParameters parameter) {
        List<String> modelCredits = new ArrayList<>();
        modelCredits.add("differentiated");
        modelCredits.add("authentic");

        if (parameter.getModelCredit() == null || parameter.getModelCredit().isEmpty()) {
            addLogs(LocalTime.now() + ": ModelCredit отсуствует.");
            return true;
        } else if (parameter.getLoanSum() == null || parameter.getLoanSum().equals(new BigDecimal(0))){
            addLogs(LocalTime.now() + ": LoanSum отсуствует.");
            return true;
        } else if (parameter.getNumberOfPeriods() == null || parameter.getNumberOfPeriods().equals(new BigDecimal(0))) {
            addLogs(LocalTime.now() + ": NumberOfPeriods отсуствует.");
            return true;
        } else  if (parameter.getInterestRate() == null || parameter.getInterestRate().equals(new BigDecimal(0))) {
            addLogs(LocalTime.now() + ": InterestRate отсуствует.");
            return true;
        } else  if (parameter.getDate() == null) {
            addLogs(LocalTime.now() + ": date format error.");
            return true;
        } else if (!modelCredits.contains(parameter.getModelCredit())) {
            addLogs(LocalTime.now() + ": ModelCredit error.");
            return true;
        } else {
            return false;
        }
    }
}







