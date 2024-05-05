package tictactoe;

import com.sun.source.tree.BreakTree;

import java.util.Scanner;

public class Main {
    static TicTacToe ticTacToe;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ticTacToe = new TicTacToe();
        ticTacToe.print();
        while (true) {
            if (readCoordinates()) {
                return;
            }
            if (aiTurn()) {
                return;
            }
        }
    }

    static boolean readCoordinates() {
        while (true) {
            System.out.print("Enter the coordinates: > ");
            String input = scanner.nextLine();
            if (!input.matches("\\d+ \\d+")) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int row = Integer.parseInt(input.split(" ")[0]);
            int column = Integer.parseInt(input.split(" ")[1]);
            try {
                TicTacToe.Result result = ticTacToe.set(row, column);
                ticTacToe.print();
                if (result == TicTacToe.Result.NotFinish) {
                    return false;
                }
                String msg = switch (result) {
                    case XWin -> "X wins";
                    case OWin -> "O wins";
                    case Draw -> "Draw";
                    case NotFinish -> null;
                };
                System.out.println(msg);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    static boolean aiTurn() {
        String msg = switch (ticTacToe.autoGenWithEasyMode()) {
            case XWin -> "X wins";
            case OWin -> "O wins";
            case Draw -> "Draw";
            case NotFinish -> null;
        };
        ticTacToe.print();
        if (msg == null) {
            return false;
        }
        System.out.println(msg);
        return true;
    }
}
