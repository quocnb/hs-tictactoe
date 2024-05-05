package tictactoe;

import java.util.Random;

public class TicTacToe {
    public enum Result {
        XWin, Draw, OWin, NotFinish
    }

    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY = ' ';
    final int tableSize;
    final private int[][] table;
    private int xCount = 0;
    private int oCount = 0;

    TicTacToe() {
        tableSize = 3;
        table = new int[3][3];
    }

    TicTacToe(int size, String initData) {
        assert initData.length() == size * size;
        tableSize = size;
        table = new int[tableSize][tableSize];
        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table.length; column++) {
                char c = initData.charAt(row * tableSize + column);
                if (c == X) {
                    xCount += 1;
                    table[row][column] = 1;
                } else if (c == O) {
                    oCount += 1;
                    table[row][column] = 2;
                } else {
                    table[row][column] = 0;
                }
            }
        }
    }

    void print() {
        StringBuilder builder = new StringBuilder();
        int lineLength = table.length + (table.length + 1) + 2;
        builder.append("-".repeat(lineLength)).append("\n");
        for (int row = 0; row < table.length; row++) {
            builder.append("| ");
            for (int column = 0; column < table.length; column++) {
                char value = EMPTY;
                if (table[row][column] == 1) {
                    value = X;
                } else if (table[row][column] == 2) {
                    value = O;
                }
                builder.append(value).append(" ");
            }
            builder.append("|").append("\n");
        }
        builder.append("-".repeat(lineLength)).append("\n");
        System.out.print(builder);
    }

    Result set(int row, int col, boolean xPlayer) throws IllegalArgumentException {
        if (row < 1 || row > tableSize || col < 1 || col > tableSize) {
            String message = String.format("Coordinates should be from 1 to %d!", tableSize);
            throw new IllegalArgumentException(message);
        }
        if (table[row - 1][col - 1] != 0) {
            throw new IllegalArgumentException("This cell is occupied! Choose another one!");
        }
        table[row - 1][col - 1] = xPlayer ? 1 : 2;
        return checkResult();
    }

    private Result checkResult() {
        int rowResult;
        int columnResult;
        int leftCrossResult = 1;
        int rightCrossResult = 1;
        int totalResult = 1;
        for (int i = 0; i < tableSize; i++) {
            rowResult = 1;
            columnResult = 1;
            for (int j = 0; j < tableSize; j++) {
                rowResult *= table[i][j];
                columnResult *= table[j][i];
                if (i == j) {
                    leftCrossResult *= table[i][j];
                }
                if (i + j == 2) {
                    rightCrossResult *= table[i][j];
                }
                totalResult = totalResult * table[i][j];
            }
            if (rowResult == 1 || columnResult == 1) {
                return Result.XWin;
            } else if (rowResult == 8 || columnResult == 8) {
                return Result.OWin;
            }
        }
        if (leftCrossResult == 1 || rightCrossResult == 1) {
            return Result.XWin;
        } else if (leftCrossResult == 8 || rightCrossResult == 8) {
            return Result.OWin;
        }
        return totalResult == 0 ? Result.NotFinish : Result.Draw;
    }

    Result autoGenWithEasyMode(boolean xPlayer) {
        System.out.println("Making move level \"easy\"");
        while (true) {
            int r = new Random().nextInt(tableSize);
            int c = new Random().nextInt(tableSize);
            if (table[r][c] == 0) {
                table[r][c] = xPlayer ? 1 : 2;
            } else if (table[c][r] == 0) {
                table[c][r] = xPlayer ? 1 : 2;
            } else {
                continue;
            }
            break;
        }
        return checkResult();
    }
}
