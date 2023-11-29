package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс для демонстрации и анализа парадокса Монти Холла.
 */
public class MontyHallGame {

    // Количество испытаний в симуляции.
    private final int TOTAL_TRIALS;

    // Логгер для вывода информации и ошибок.
    private static final Logger logger = LoggerFactory.getLogger(MontyHallGame.class);

    public MontyHallGame(int total_trials) {
        this.TOTAL_TRIALS = total_trials;
    }

    /**
     * Отображает результаты каждой игры.
     *
     * @param results Результаты игр.
     * @param moves   Выборы в каждой игре.
     */
    public void displayGameResults(Map<Integer, Boolean> results, Map<Integer, List<Integer>> moves) {
        for (int i = 1; i <= TOTAL_TRIALS; i++) {
            List<Integer> gameMoves = moves.get(i);
            if (gameMoves != null && gameMoves.size() >= 3) {
                // Получение и вывод результатов каждой игры.
                int playerChoice = gameMoves.get(0);
                int goatDoor = gameMoves.get(1);
                int switchChoice = gameMoves.get(2);
                boolean win = results.get(i);

                // Формирование и вывод сообщения о ходе и результате игры.
                String explanation = String.format("Игра %d: Игрок выбрал дверь %d, Ведущий открывает дверь %d%s",
                        i, playerChoice, goatDoor,
                        switchChoice > 0 ? ", Игрок выбирает дверь " + switchChoice : ", Игрок оставляет свой выбор");

                logger.info(explanation + (win ? " - Игрок выиграл" : " - Игрок проиграл"));
            } else {
                // Вывод ошибки, если данные игры некорректны.
                logger.error("Ошибка при обработке хода игры " + i);
            }
        }
    }

    /**
     * Выводит статистику по всем играм.
     *
     * @param results Результаты игр.
     */
    public void displayStatistics(Map<Integer, Boolean> results) {
        long positiveResults = results.values().stream().filter(Boolean::booleanValue).count();
        // Расчет и вывод статистики по играм.
        int negativeResults = TOTAL_TRIALS - (int) positiveResults;
        double positivePercentage = (double) positiveResults / TOTAL_TRIALS * 100;

        logger.info("==================== Статистика: ====================");
        logger.info("Позитивные результаты: {}", positiveResults);
        logger.info("Негативные результаты: {}", negativeResults);
        logger.info("Процент позитивных результатов: {}%", positivePercentage);
    }

    /**
     * Симулирует одну игру в парадоксе Монти Холла.
     *
     * @param moves      Ходы в игре.
     * @param gameNumber Номер текущей игры.
     * @return Возвращает true, если игрок выиграл.
     */
    public boolean playMontyHallGame(Map<Integer, List<Integer>> moves, int gameNumber) {
        // Генерация случайных выборов дверей и решений игрока.
        int carDoor = ThreadLocalRandom.current().nextInt(3) + 1;
        int playerChoice = ThreadLocalRandom.current().nextInt(3) + 1;
        int goatDoor = selectGoatDoor(carDoor, playerChoice);

        boolean switchChoice = ThreadLocalRandom.current().nextBoolean();
        if (switchChoice) {
            playerChoice = switchPlayerChoice(playerChoice, goatDoor);
        }

        // Сохранение ходов игры.
        saveGameMove(moves, gameNumber, playerChoice, goatDoor, switchChoice ? playerChoice : 0);
        return playerChoice == carDoor;
    }

    /**
     * Выбирает дверь с козой, которую откроет ведущий.
     *
     * @param carDoor      Дверь с автомобилем.
     * @param playerChoice Выбор игрока.
     * @return Номер двери с козой.
     */
    public int selectGoatDoor(int carDoor, int playerChoice) {
        List<Integer> doors = new ArrayList<>(Arrays.asList(1, 2, 3));
        doors.removeIf(door -> door == carDoor || door == playerChoice);
        return doors.get(ThreadLocalRandom.current().nextInt(doors.size()));
    }

    /**
     * Изменяет выбор игрока, если он решает сменить дверь.
     *
     * @param playerChoice Текущий выбор игрока.
     * @param goatDoor     Дверь с козой.
     * @return Новый выбор игрока.
     */
    public int switchPlayerChoice(int playerChoice, int goatDoor) {
        // Логика изменения выбора игрока.
        return 6 - playerChoice - goatDoor; // Так как сумма номеров всех дверей равна 6.
    }

    /**
     * Сохраняет ходы каждой игры.
     *
     * @param moves        Словарь для сохранения ходов.
     * @param gameNumber   Номер игры.
     * @param playerChoice Выбор игрока.
     * @param goatDoor     Дверь с козой.
     * @param switchChoice Измененный выбор игрока.
     */
    public void saveGameMove(Map<Integer, List<Integer>> moves, int gameNumber, int playerChoice, int goatDoor,
                             int switchChoice) {
        moves.put(gameNumber, Arrays.asList(playerChoice, goatDoor, switchChoice));
    }

}
