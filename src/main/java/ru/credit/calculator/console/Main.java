package ru.credit.calculator.console;


import ru.credit.calculator.console.service.Console;
import ru.credit.calculator.console.service.Listener;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static ru.credit.calculator.console.service.CheckPath.checkPath;


public class Main {
    public static void main(String[] args) {

        //Проверка и создание каталогов
        List<String> paths = checkPath(args[0]);

        //Запускает два потока: управление консолью и слушателя.
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Listener.runListener(paths.get(0), paths.get(1));
                } catch (NoClassDefFoundError| NoSuchElementException | InterruptedException | JAXBException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Console.runConsoleApplication();
            }
        }).start();
    }
}
