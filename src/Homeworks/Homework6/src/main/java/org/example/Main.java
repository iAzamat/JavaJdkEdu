package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    // Количество испытаний в симуляции.
    private static final int TOTAL_TRIALS = 10000;

    /**
     * Основная точка входа в программу.
     * Инициализирует и запускает симуляцию парадокса Монти Холла.
     */
    public static void main(String[] args) {
        // Хранение результатов каждого испытания.
        Map<Integer, Boolean> results = new HashMap<>();
        // Хранение выборов в каждом испытании.
        Map<Integer, List<Integer>> moves = new HashMap<>();
        MontyHallGame mhg = new MontyHallGame(TOTAL_TRIALS);

        for (int i = 1; i <= TOTAL_TRIALS; i++) {
            results.put(i, mhg.playMontyHallGame(moves, i));
        }

        // Отображение результатов и статистики.
        mhg.displayGameResults(results, moves);
        mhg.displayStatistics(results);
    }
}
