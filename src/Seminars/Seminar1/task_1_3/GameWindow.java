package Seminars.Seminar1.task_1_3;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 560;
    private static final int WINDOW_WIDTH = 508;
    private static final int WINDOW_POS_X = 800;
    private static final int WINDOW_POS_Y = 300;
    static public final String WINDOW_NAME = "Игра в крестики-нолики";
    GameSettings gameSettings;
    GameMap gameMap;

    GameWindow() {
        //свойства окна
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POS_X, WINDOW_POS_Y);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(WINDOW_NAME);
        setResizable(false);

        //элементы основная часть
        gameSettings = new GameSettings(this);
        gameMap = new GameMap();
        //элементы нижняя часть
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Exit");
        JPanel controlPanel = new JPanel(new GridLayout(1, 2));

        //обработчики кнопок
        startButton.addActionListener(e -> {
//                int pos_x = WINDOW_POS_X + (WINDOW_WIDTH / 2 - GameSettings.WINDOW_WIDTH / 2);
//                int pos_y = WINDOW_POS_Y + (WINDOW_HEIGHT / 2 - GameSettings.WINDOW_HEIGHT / 2);
            gameSettings.setLocationRelativeTo(GameWindow.this);
            gameSettings.setVisible(true);
        });
        stopButton.addActionListener(e -> System.exit(0));

        //отрисовка
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        add(controlPanel, BorderLayout.PAGE_END);
        add(gameMap);
        gameMap.setVisible(false);
        setVisible(true);
    }

    void startNewGame(int mode, int size_x, int size_y, int win_len) {
        gameMap.startNewGame(mode, size_x, size_y, win_len);
        gameMap.setVisible(true);
    }
}