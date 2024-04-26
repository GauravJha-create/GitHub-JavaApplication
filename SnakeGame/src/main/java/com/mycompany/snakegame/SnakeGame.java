package com.mycompany.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JPanel implements ActionListener {
    private static final int BOARD_WIDTH = 400;
    private static final int BOARD_HEIGHT = 400;
    private static final int DOT_SIZE = 10;
    private static final int ALL_DOTS = (BOARD_WIDTH * BOARD_HEIGHT) / (DOT_SIZE * DOT_SIZE);
    private static final int DELAY = 100;
    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];
    private int dots;
    private int foodX;
    private int foodY;
    private boolean isGameRunning = true;
    private boolean isPaused = false;
    private boolean isMovingRight = true;
    private boolean isMovingLeft = false;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private Timer timer;

    public SnakeGame() {
        initGame();
    }

    private void initGame() {
        setBackground(Color.black);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_SPACE) {
                    if (isGameRunning) {
                        if (isPaused) {
                            timer.start();
                            isPaused = false;
                        } else {
                            timer.stop();
                            isPaused = true;
                        }
                    }
                } else if (key == KeyEvent.VK_RIGHT && !isMovingLeft) {
                    isMovingRight = true;
                    isMovingUp = false;
                    isMovingDown = false;
                } else if (key == KeyEvent.VK_LEFT && !isMovingRight) {
                    isMovingLeft = true;
                    isMovingUp = false;
                    isMovingDown = false;
                } else if (key == KeyEvent.VK_UP && !isMovingDown) {
                    isMovingUp = true;
                    isMovingRight = false;
                    isMovingLeft = false;
                } else if (key == KeyEvent.VK_DOWN && !isMovingUp) {
                    isMovingDown = true;
                    isMovingRight = false;
                    isMovingLeft = false;
                }
            }
        });

        startGame();
    }

    private void startGame() {
        dots = 3; // Initial length of the snake
        for (int i = 0; i < dots; i++) {
            x[i] = 50 - i * DOT_SIZE;
            y[i] = 50;
        }

        placeFood();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (isGameRunning) {
            g.setColor(Color.red);
            g.fillOval(foodX, foodY, DOT_SIZE, DOT_SIZE);

            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(Color.green.darker());
                }
                g.fillRect(x[i], y[i], DOT_SIZE, DOT_SIZE);
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void placeFood() {
        int r = (int) (Math.random() * (BOARD_WIDTH / DOT_SIZE));
        foodX = r * DOT_SIZE;

        r = (int) (Math.random() * (BOARD_HEIGHT / DOT_SIZE));
        foodY = r * DOT_SIZE;
    }

    private void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (isMovingRight) {
            x[0] += DOT_SIZE;
        } else if (isMovingLeft) {
            x[0] -= DOT_SIZE;
        } else if (isMovingUp) {
            y[0] -= DOT_SIZE;
        } else if (isMovingDown) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {
        for (int i = dots; i > 0; i--) {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                isGameRunning = false;
            }
        }

        if (y[0] >= BOARD_HEIGHT || y[0] < 0 || x[0] >= BOARD_WIDTH || x[0] < 0) {
            isGameRunning = false;
        }

        if (!isGameRunning) {
            timer.stop();
        }
    }

    private void checkFood() {
        if ((x[0] == foodX) && (y[0] == foodY)) {
            dots++;
            placeFood();
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font font = new Font("Arial", Font.BOLD, 30);
        FontMetrics metrics = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(msg, (BOARD_WIDTH - metrics.stringWidth(msg)) / 2, BOARD_HEIGHT / 2);

        String scoreMsg = "Score: " + (dots - 3);
        g.drawString(scoreMsg, (BOARD_WIDTH - metrics.stringWidth(scoreMsg)) / 2, BOARD_HEIGHT / 2 + 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameRunning) {
            checkFood();
            checkCollision();
            move();
        }
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.add(new SnakeGame(), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}