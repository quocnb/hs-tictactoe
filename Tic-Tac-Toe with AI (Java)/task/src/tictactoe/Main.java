package tictactoe;

import java.util.Scanner;

public class Main {
    static TicTacToe ticTacToe;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter the cells: ");
        String input = scanner.nextLine().trim();
        ticTacToe = new TicTacToe(3, input);
        ticTacToe.print();
        readCoordinates();
    }

    static void readCoordinates() {
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
                String msg = switch (result) {
                    case XWin -> "X wins";
                    case OWin -> "O wins";
                    case Draw -> "Draw";
                    case NotFinish -> "Game not finished";
                };
                System.out.println(msg);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
