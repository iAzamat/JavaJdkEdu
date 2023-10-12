package Seminars.Seminar2.Task1;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final String WINDOW_NAME = "Chat server";

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    JPanel grid = new JPanel(new GridLayout(2, 1));
    JPanel buttons = new JPanel(new GridLayout(1, 2));
    JScrollPane scrollPanel = new JScrollPane(log,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    ServerListener server;

    public ServerWindow(ServerListener server) {
        this.server = server;
        log.setEditable(false);
        log.setBackground(Color.GRAY);
        btnStart.addActionListener(e -> {
            server.start();
            log.append(server.getServerStatus());
        });

        btnStop.addActionListener(e -> {
            server.stop();
            log.append(server.getServerStatus());
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle(WINDOW_NAME);
        setAlwaysOnTop(true);

        buttons.add(btnStart);
        buttons.add(btnStop);
        grid.add(scrollPanel);
        grid.add(buttons);
        add(grid);

        setVisible(true);
    }
}
