package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Основной класс бота, отвечающий за обработку входящих сообщений.</p>
 *
 * <p>При старте (команда /start) шлёт приветствие и кнопку "Generate Word".
 * При нажатии на кнопку отсылает пользователю случайное слово, полученное из {@link WordsRepository}.</p>
 */
public class RandomEnglishWordBot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(RandomEnglishWordBot.class);


    private final WordsRepository wordsRepository;

    /**
     * <p>Создаёт бота и инициализирует репозиторий слов.</p>
     *
     * @param wordsRepository экземпляр класса {@link WordsRepository}
     */
    public RandomEnglishWordBot(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }

    /**
     * @return Имя (username) бота, заданное в BotConfig
     */
    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    /**
     * @return Токен бота, выданный BotFather
     */
    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    /**
     * <p>Обрабатывает входящие сообщения.</p>
     *
     * @param update объект события (сообщение, нажатие кнопки и т.д.)
     */
    @Override
    public void onUpdateReceived(Update update) {
        // Проверяем, пришло ли текстовое сообщение от пользователя
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = String.valueOf(update.getMessage().getChatId());
            String userText = update.getMessage().getText();

            if ("/start".equals(userText)) {
                // Команда /start
                String welcomeText = "Привет!\nНажми кнопку, чтобы получить случайное слово:";
                sendTextWithKeyboard(chatId, welcomeText);
            } else if ("Generate Word".equals(userText)) {
                // Нажали кнопку
                String randomWord = wordsRepository.getRandomWord();
                sendTextMessage(chatId, randomWord);
            } else {
                // Любой другой текст
                sendTextMessage(chatId, "Используйте /start или нажмите кнопку \"Generate Word\".");
            }
        }
    }

    /**
     * <p>Отправляет простое текстовое сообщение пользователю.</p>
     *
     * @param chatId идентификатор чата (String)
     * @param text   текст сообщения
     */
    private void sendTextMessage(String chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Ошибка при отправке сообщения", e);
        }
    }

    /**
     * <p>Отправляет сообщение с одной кнопкой, используя обычную Reply-клавиатуру.</p>
     *
     * @param chatId идентификатор чата
     * @param text   текст приветствия
     */
    private void sendTextWithKeyboard(String chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);

        // Создаём раскладку клавиатуры
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);

        // Одна строка (row) с одной кнопкой
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("Generate Word"));

        // Добавляем список строк (пока что только одна строка)
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row);

        keyboardMarkup.setKeyboard(rows);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Ошибка при отправке сообщения", e);
        }
    }
}