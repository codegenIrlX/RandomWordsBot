# RandomWordBot

Это простой Telegram-бот на Java, который отправляет одно случайное слово из списка при нажатии на кнопку. Слова для бота берутся из репозитория [english-words](https://github.com/dwyl/english-words) – конкретно, используется файл `words_alpha.txt`.

## Содержание

- [Требования](#требования)
- [Создание бота в BotFather](#создание-бота-в-botfather)
- [Настройка проекта](#настройка-проекта)
- [Сборка и запуск](#сборка-и-запуск)

## Требования

- **Java JDK 11** (или выше)
- **Gradle** (для сборки проекта)

## Создание бота в BotFather

1. **Откройте Telegram и найдите BotFather.**  
   В поиске введите `@BotFather` и начните диалог.

2. **Создайте нового бота.**  
   Отправьте команду `/newbot` и следуйте инструкциям:
    - Введите имя для бота (например, _Random English Word Bot_).
    - Введите уникальное имя пользователя для бота (например, _RandomEnglishWordBot_). Имя пользователя должно оканчиваться на `bot` (например, `RandomEnglishWordBot`).

3. **Получите токен.**  
   После создания бота BotFather пришлёт вам токен (например, `123456789:ABCdefGhIJKlmnopQRStuvWXyz`). Сохраните этот токен – он понадобится для настройки проекта.

## Настройка проекта

1. **Клонируйте/скачайте проект.**  

2. **Настройте BotConfig.**  
   Отредактируйте файл `BotConfig.java` с содержимым примерно такого вида:

   ```java
   package org.example;

   public class BotConfig {
       public static final String BOT_USERNAME = System.getenv().getOrDefault("TELEGRAM_BOT_USERNAME", "Имя вашего бота");
        public static final String BOT_TOKEN = System.getenv().getOrDefault("TELEGRAM_BOT_TOKEN", "Токен вашего бота");
   }

## Сборка и запуск

### Сборка проекта

В терминале, находясь в корневой директории проекта, выполните команду:

```bash
./gradlew build

