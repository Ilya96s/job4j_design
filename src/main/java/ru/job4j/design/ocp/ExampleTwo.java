package ru.job4j.design.ocp;
/**
 *
 * Пример № 2
 *
 * Нарушение принципа OCP заключается в том, что SmtpMailer не закрыт для изменения. Для того что бы поменять способ хранения логов
 * приходится изменять класс
 *
 * @author Ilya Kaltygin
 */
public class ExampleTwo {
    private FileLogger logger;

    public ExampleTwo(FileLogger logger) {
        logger = new FileLogger();
    }

    public void message(String message) {
        logger.log(String.format("Отправлено '{}'", message));
    }

    public static class FileLogger {

        public void log(String message) {

        }

    }

    public static class DBLogger {

        public void log(String message) {

        }
    }
}
