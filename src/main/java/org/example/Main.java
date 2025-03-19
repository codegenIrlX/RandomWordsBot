package org.example;

 import org.telegram.telegrambots.meta.TelegramBotsApi;
 import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    /**
     * <p>Запуск бота. Создаём репозиторий слов, экземпляр бота и регистрируем его в TelegramBotsApi.</p>
     */
    public static void main(String[] args) {
        try {
            // Загружаем слова
            WordsRepository wordsRepository = new WordsRepository();

            // Создаём экземпляр бота
            RandomEnglishWordBot bot = new RandomEnglishWordBot(wordsRepository);

            // Регистрируем его в Telegram
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);

            System.out.println("Bot started successfully!");
        } catch (Exception e) {
            System.err.println("Failed to start bot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}