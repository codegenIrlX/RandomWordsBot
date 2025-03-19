package org.example;

/**
 * <p>Хранит конфигурационные параметры Telegram-бота.</p>
 */
public class BotConfig {

    /**
     * Имя бота (username), которое вы указали при создании у BotFather.
     */
    public static final String BOT_USERNAME = System.getenv().getOrDefault("TELEGRAM_BOT_USERNAME", "");

    /**
     * Токен бота, полученный от BotFather.
     */
    public static final String BOT_TOKEN = System.getenv().getOrDefault("TELEGRAM_BOT_TOKEN", "");

    /**
     * Путь к файлу со списком английских слов в resources.
     */
    public static final String WORDS_FILE_PATH = "/words_alpha.txt";

    private BotConfig() {
    }
}