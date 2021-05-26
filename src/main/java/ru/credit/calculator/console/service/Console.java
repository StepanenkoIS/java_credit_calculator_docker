package ru.credit.calculator.console.service;

import java.util.Scanner;

public class Console {

    private static String info = "Список достпуных команд: \n" +
            "- exit - выход из приложения \n" +
            "- logs - вывод логов. \n" +
            "- logs -cl - очистка логов. \n" +
            "- info - получить список команд.";

    // Управление косолью
    public static void runConsoleApplication() {
        System.out.println("Для получения списка доступных команд введите 'info'");
        Scanner scanner = new Scanner(System.in, "UTF-8");
        boolean flag = true;
        while (flag) {
            System.out.print("Введите значение: -> ");
            switch (scanner.nextLine()) {
                case "exit":
                    flag = false;
                    Listener.running();
                    break;
                case "info":
                    System.out.println(info);
                    break;
                case "logs":
                    Logs.outputLogs();
                    break;
                case "logs -cl":
                    Logs.clearLogs();
                    break;
                default:
                    System.out.println("Введенное значение неверно. Повторите ввод.");
            }
        }
    }
}





