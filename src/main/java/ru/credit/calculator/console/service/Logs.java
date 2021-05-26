package ru.credit.calculator.console.service;

import java.util.concurrent.CopyOnWriteArrayList;

public class Logs {

    private static CopyOnWriteArrayList<String> logs= new CopyOnWriteArrayList<String>();

    //Для тестирования
    public static CopyOnWriteArrayList<String> getLogs() {
        return logs;
    }

    //Метод для записи логов
    public static void addLogs(String log) {
        logs.add(log);
    }

    //Метод для вывода логов
    public static void clearLogs() {
        logs.clear();
    }

    //Метод для очистки логов
    public static void outputLogs() {
        for (String log:logs) {
            System.out.println(log);
        }
    }

}
