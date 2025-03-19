package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * <p>Класс для чтения и хранения списка английских слов из файла.</p>
 *
 * <p>Использует Stream API для чтения файла из resources и фильтрует пустые строки.
 * Список слов загружается один раз при создании экземпляра.</p>
 */
public class WordsRepository {

    private final List<String> words;

    /**
     * <p>Создаёт новый {@code WordsRepository} и загружает слова из файла.</p>
     *
     * @throws IOException если не удаётся прочитать файл
     */
    public WordsRepository() throws IOException {
        try (InputStream in = getClass().getResourceAsStream(BotConfig.WORDS_FILE_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            // Считываем все непустые строки в список
            words = reader.lines()
                    .filter(line -> !line.isBlank())
                    .collect(Collectors.toList());
        }

        // Если words пуст - возможно, файла нет или он некорректен
        if (words.isEmpty()) {
            throw new IOException("Список слов пуст. Проверьте наличие файла " + BotConfig.WORDS_FILE_PATH);
        }
    }

    /**
     * <p>Возвращает одно случайное слово из списка.</p>
     *
     * @return Случайное английское слово
     */
    public String getRandomWord() {
        int index = ThreadLocalRandom.current().nextInt(words.size());
        return words.get(index);
    }
}