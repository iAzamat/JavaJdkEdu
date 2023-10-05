package Seminars.Seminar1.task_1_3;

import javax.swing.*;
import java.awt.*;

public class GameSettings extends JFrame {

    static public final int WINDOW_HEIGHT = 240;
    static public final int WINDOW_WIDTH = 320;
    static public int sliderSizeValue = 3;
    static public int sliderWinValue = 3;
    //public boolean modeValue = false;

    JLabel labelMode = new JLabel("Выберите режим игры");
    JRadioButton pvc = new JRadioButton("Человек против компьютера", true);
    JRadioButton pvp = new JRadioButton("Человек против человека", false);
    ButtonGroup bg = new ButtonGroup();
    JLabel labelSize = new JLabel(String.format("Установленный размер поля: %d", sliderSizeValue));
    JSlider sliderSize = new JSlider(3, 10, sliderSizeValue);
    JLabel labelWin = new JLabel(String.format("Установленная длина: %d", sliderWinValue));
    JSlider sliderWin = new JSlider(3, 10, sliderWinValue);
    JPanel grid = new JPanel(new GridLayout(4, 1));
    JPanel top = new JPanel(new GridLayout(3, 1));
    JPanel middle = new JPanel(new GridLayout(3, 1));
    JPanel bottom = new JPanel(new GridLayout(3, 1));
    JButton buttonStart = new JButton("Запуск игры");


    GameSettings(GameWindow gameWindow) {
        setTitle("Окно настроек");
        //setLocation(pos_x, pos_y);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        bg.add(pvc);
        bg.add(pvp);

        top.add(labelMode);
        top.add(pvc);
        top.add(pvp);

        middle.add(new JLabel("Выберите размеры поля"));
        middle.add(labelSize);
        middle.add(sliderSize);

        bottom.add(new JLabel("Выберите длину для победы"));
        bottom.add(labelWin);
        bottom.add(sliderWin);

        grid.add(top);
        grid.add(middle);
        grid.add(bottom);
        grid.add(buttonStart);
        add(grid);

        sliderSize.addChangeListener(e -> {
            sliderSizeValue = sliderSize.getValue();
            labelSize.setText(String.format("Установленный размер поля: %d", sliderSizeValue));
        });

        sliderWin.addChangeListener(e -> {
            sliderWinValue = sliderWin.getValue();
            labelWin.setText(String.format("Установленная длина: %d", sliderWinValue));
        });

        buttonStart.addActionListener(e -> {
            int gamemode;
            if(pvc.isSelected()){
                gamemode = GameMap.MODE_PVC;
            } else if (pvp.isSelected()) {
                gamemode = GameMap.MODE_PVP;
            } else {
                throw new RuntimeException("Unknown game mode");
            }
            //modeValue = pvp.isSelected();
            if (sliderWinValue > sliderSizeValue) {
                sliderWin.setValue(sliderSizeValue);
            }
            gameWindow.startNewGame(gamemode, sliderSizeValue, sliderSizeValue, sliderWinValue);
            setVisible(false);
        });
    }
}
