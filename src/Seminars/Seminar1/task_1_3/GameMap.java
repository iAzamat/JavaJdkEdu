package Seminars.Seminar1.task_1_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameMap extends JPanel {
    private char[][] field;
    private int panelWidth;
    private int panelHeight;
    private int cellHeight;
    private int cellWidth;
    private int fieldSizeX;
    private int fieldSizeY;
    public static final int DOT_PADDING = 5;
    private int winLength;
    private boolean isGameOver;
    private boolean isInitialized;
    private static String MSG_WIN_PLAYER1 = "Победил игрок!";
    private static String MSG_WIN_PLAYER2 = "Победил компьютер!";
    private static final String MSG_DRAW = "Ничья!";
    private final int PLAYER1_DOT = 1;
    private final int PLAYER2_DOT = 2;
    private final int EMPTY_DOT = 0;
    private int gameOverType;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_PLAYER1 = 1;
    private static final int STATE_WIN_PLAYER2 = 2;
    private static final Random RANDOM = new Random();
    private int gameMode;
    /* ===================== */
    public static final int MODE_PVC = 0;
    public static final int MODE_PVP = 1;
    private boolean checkMove = true;
    /* ===================== */

    private void update(MouseEvent e) {
        if (isGameOver || !isInitialized) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
        if (gameMode == 0) {
            playerTurn(cellX, cellY, PLAYER1_DOT);
            repaint();
            if (checkEndGame(PLAYER1_DOT, PLAYER1_DOT)) return;
            aiTurn();
            repaint();
            if (checkEndGame(PLAYER2_DOT, PLAYER2_DOT)) return;
        } else if (gameMode == 1) {
            int dot;
            if (checkMove) {
                dot = PLAYER1_DOT;
                checkMove = false;
            } else {
                dot = PLAYER2_DOT;
                checkMove = true;
            }
            playerTurn(cellX, cellY, dot);
            repaint();
            if (checkEndGame(dot, dot)) return;
        }
    }

    private void playerTurn(int x, int y, int dot) {
        field[y][x] = (char) dot;
    }

    /* Рендер */
    private void render(Graphics g) {
        if (!isInitialized) return;
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / fieldSizeX;
        cellWidth = panelWidth / fieldSizeY;

        g.setColor(Color.BLACK);
        for (int h = 0; h < fieldSizeY; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int w = 0; w < fieldSizeX; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }
        repaint();

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT) continue;

                if (field[y][x] == PLAYER1_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING, cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                } else if (field[y][x] == PLAYER2_DOT) {
                    g.setColor(new Color(0xff0000));
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING, cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("Unexpected value " + field[y][x] + " in cell: x=" + x + " y=" + y);
                }
            }
        }
        if (isGameOver) showMessageGameOver(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
    /* Рендер */

    /* Инициализация поля */
    GameMap() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
        isInitialized = false;
    }

    void startNewGame(int mode, int size_x, int size_y, int win_len) {
        System.out.printf("Mode: %d;\nSize: x=%d, y=%d;\nWin Length: %d",
                mode, size_x, size_y, win_len);
        /* =============== */
        this.gameMode = mode;
        this.fieldSizeX = size_x;
        this.fieldSizeY = size_y;
        this.winLength = win_len;
        if (mode == 0) {
            MSG_WIN_PLAYER1 = "Победил игрок!";
            MSG_WIN_PLAYER2 = "Победил компьютер!";
        } else if (mode == 1) {
            MSG_WIN_PLAYER1 = "Победил 1 игрок!";
            MSG_WIN_PLAYER2 = "Победил 2 игрок!";
        } else {
            throw new RuntimeException("Unexpected gameMode: " + mode);
        }
        /* =============== */
        initMap();
        isGameOver = false;
        isInitialized = true;
        repaint();
    }

    private void initMap() {
        field = new char[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }
    /* Инициализация поля */

    /* Проверка на валидность */
    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }
    /* Проверка на валидность */

    /* AI */
    private boolean turnAIWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(j, i)) {
                    field[i][j] = PLAYER2_DOT;
                    if (checkWin(PLAYER2_DOT)) return true;
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    private boolean turnHumanWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(j, i)) {
                    field[i][j] = PLAYER1_DOT;
                    if (checkWin(PLAYER1_DOT)) {
                        field[i][j] = PLAYER2_DOT;
                        return true;
                    }
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    private void aiTurn() {
        if (turnAIWinCell()) return;
        if (turnHumanWinCell()) return;
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = PLAYER2_DOT;
    }
    /* AI */

    /* Проверка победы */
    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    private boolean checkWin(int c) {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (checkLine(i, j, 1, 0, winLength, c)) return true;
                if (checkLine(i, j, 1, 1, winLength, c)) return true;
                if (checkLine(i, j, 0, 1, winLength, c)) return true;
                if (checkLine(i, j, 1, -1, winLength, c)) return true;
            }
        }
        return false;
    }

    private boolean checkLine(int x, int y, int vx, int vy, int len, int c) {
        final int far_x = x + (len - 1) * vx;
        final int far_y = y + (len - 1) * vy;
        if (!isValidCell(far_x, far_y)) return false;
        for (int i = 0; i < len; i++) {
            if (field[y + i * vy][x + i * vx] != c) return false;
        }
        return true;
    }

    private boolean checkEndGame(int dot, int gameOverType) {
        if (checkWin(dot)) {
            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }
        return false;
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameOverType) {
            case STATE_DRAW -> g.drawString(MSG_DRAW, 180, getHeight() / 2);
            case STATE_WIN_PLAYER1 -> g.drawString(MSG_WIN_PLAYER1, 70, getHeight() / 2);
            case STATE_WIN_PLAYER2 -> g.drawString(MSG_WIN_PLAYER2, 20, getHeight() / 2);
            default -> throw new RuntimeException("Unexpected gameOver state: " + gameOverType);
        }
    }
    /* Проверка победы */
}