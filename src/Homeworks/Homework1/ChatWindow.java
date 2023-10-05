package Homeworks.Homework1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatWindow extends JFrame {
    private int WINDOW_POS_X = 100;
    private int WINDOW_POS_Y = 300;
    private int WINDOW_WIDTH = 640;
    private int WINDOW_HEIGHT = 480;
    private String WINDOW_TITLE = "Чат с логированием, сервер :";
    private String login;
    private String server;
    private LogHandler logHandler;
    private String msgText;
    private JButton sendButton = new JButton("Отправить");
    private JLabel inputLabel = new JLabel("Введите сообщение");
    private JTextField inputField = new JTextField();
    private JTextArea outputField = new JTextArea();
    JScrollPane scrollPanel = new JScrollPane(outputField,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JPanel inputGrid = new JPanel(new GridLayout(4, 1));

    ChatWindow(String login, String server, LogHandler logHandler) throws IOException {
        this.logHandler = logHandler;
        this.login = login;
        this.server = server;
        WINDOW_TITLE = WINDOW_TITLE + server;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(WINDOW_TITLE);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        initWindow();

        //сборка окна
//        inputGrid.add(outputField);
        inputGrid.add(scrollPanel);
        inputGrid.add(inputLabel);
        inputGrid.add(inputField);
        inputGrid.add(sendButton);
        add(inputGrid);
        setVisible(true);
    }

    private void initWindow() throws IOException {
        //инициализация поля вывода
        outputField.setEditable(false);
        outputField.setBackground(Color.LIGHT_GRAY);
        StringBuilder sb = new StringBuilder();
        for (String string : logHandler.Read()) {
            sb.append(string);
            sb.append(System.lineSeparator());
        }
        outputField.setText(sb.toString());

        //поле ввода-вывода
        sendButton.addActionListener(e -> {
            String dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
            msgText = "[" + dt + "] " + login + ": " + inputField.getText();
            try {
                logHandler.Write(msgText);
                outputField.append(msgText);
                outputField.append(System.lineSeparator());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            inputField.setText("");
        });

        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                    msgText = "[" + dt + "] " + login + ": " + inputField.getText();
                    try {
                        logHandler.Write(msgText);
                        outputField.append(msgText);
                        outputField.append(System.lineSeparator());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    inputField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }
}
