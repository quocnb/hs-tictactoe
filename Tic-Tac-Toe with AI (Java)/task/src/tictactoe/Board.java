package tictactoe;

import java.util.Random;

public class Board {
    private static final char EMPTY = ' ';
    static final char PLAYER_X = 'X';
    static final char PLAYER_O = 'O';

    final private char[][] board = new char[3][3];

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    void print() {
        StringBuilder builder = new StringBuilder();
        int lineLength = board.length + (board.length + 1) + 2;
        builder.append("-".repeat(lineLength)).append("\n");
        for (int row = 0; row < board.length; row++) {
            builder.append("| ");
            for (int column = 0; column < board.length; column++) {
                builder.append(board[row][column]).append(" ");
            }
            builder.append("|").append("\n");
        }
        builder.append("-".repeat(lineLength)).append("\n");
        System.out.print(builder);
    }

    boolean isWin(char player) {
        // Check row - column
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        // Check cross
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    boolean canMove(int row, int col) {
        return board[row][col] == EMPTY;
    }

    void move(int row, int col, char sign) {
        board[row][col] = sign;
    }

    boolean canWin(int row, int col, char sign) {
        move(row, col, sign);
        boolean canWin = isWin(sign);
        reset(row, col);
        return canWin;
    }

    void random(char sign) {
        while (true) {
            int r = new Random().nextInt(3);
            int c = new Random().nextInt(3);
            if (canMove(r, c)) {
                move(r, c, sign);
                return;
            }
        }
    }

    void reset(int row, int col) {
        board[row][col] = EMPTY;
    }

    boolean isFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
