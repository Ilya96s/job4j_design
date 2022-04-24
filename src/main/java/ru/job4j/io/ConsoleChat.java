package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Работа с кодировкой файлов
 *
 * Создание программы консольный чат.
 * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * - при вводе слова «закончить» программа прекращает работу.
 * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
 *
 * @author Ilya Kaltygin
 * @version 1.2
 */
public class ConsoleChat {
    private static final String OUT = "Закончить";
    private static final String STOP = "Стоп";
    private static final String CONTINUE = "Продолжить";
    private final String path;
    private final String bootAnswer;
    List<String> log = new ArrayList<>();
    List<String> phrase = new ArrayList<>();

    /**
     * Конструктор
     * @param path Имя файла в который будет записан весь диалог между пользователем и ботом
     * @param bootAnswer Имя файла в котором находятся строки с ответами, которые использует бот
     */
    public ConsoleChat(String path, String bootAnswer) {
        this.path = path;
        this.bootAnswer = bootAnswer;
    }

    /**
     * Метод считывает данные с клавиатуры и возвращает случайные ответы.
     * По окончанию работы метод записывает диалог в текстовый лог
     */
    public void run() {
        boolean chatStatus = true;
        boolean botStatus = true;
        phrase = readPhrases();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст");
        while (chatStatus) {
            String userQuestion = scanner.nextLine();
            switch (userQuestion) {
                case STOP:
                    log.add("User: " + userQuestion);
                    botStatus = false;
                    break;

                case OUT:
                    log.add("User: " + userQuestion);
                    chatStatus = false;
                    break;

                case CONTINUE:
                    log.add("User: " + userQuestion);
                    botStatus = true;
                    break;

                default:
                    if (botStatus) {
                        String botAnswer = phrase.get((int) (Math.random() * phrase.size()));
                        log.add("User: " + userQuestion);
                        log.add("Bot: " + botAnswer);
                        System.out.println(botAnswer);
                    }
                }
            }
        serverLog(log);
        }

    /**
     * Метод считывает фразы бота из файла и сохраняет их в список
     * @return возвращает список с фразами
     */
    private List<String> readPhrases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(bootAnswer))) {
            reader.lines()
                    .forEach(phrase::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrase;
    }

    /**
     * Метод сохраняет лог чата в файл в кодировке WINDOWS-1251
     * @param log Лог чата
     */
    private void serverLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/io/log.txt", "data/io/phrases.txt");
        cc.run();
    }
}
