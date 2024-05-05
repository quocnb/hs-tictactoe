package tictactoe;

import com.sun.source.tree.BreakTree;

import java.util.Scanner;

public class Main {
    enum Player {
        User, AI_Easy;

        static public Player of(String player) {
            if (User.command().equals(player)) {
                return User;
            } else if (AI_Easy.command().equals(player)) {
                return AI_Easy;
            }
            throw new IllegalArgumentException();
        }

        String command() {
            return switch (this) {
                case User -> "user";
                case AI_Easy -> "easy";
            };
        }
    }
    static TicTacToe ticTacToe;
    static Scanner scanner = new Scanner(System.in);
    static Player[] players;

    public static void main(String[] args) {
        while (true) {
            if (playGame()) {
                break;
            }
        }
    }

    static boolean playGame() {
        while (true) {
            System.out.print("Input command: > ");
            String input = scanner.nextLine().trim();
            if ("exit".equals(input)) {
                return true;
            }
            try {
                readPlayers(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Bad parameters!");
            }
        }
        // Game Start
        ticTacToe = new TicTacToe();
        ticTacToe.print();
        int turn = 0;
        while (true) {
            if (players[turn] == Player.User) {
                if (readCoordinates(turn == 0)) {
                    return false;
                }
            } else {
                if (aiTurn(turn == 0)) {
                    return false;
                }
            }
            turn = (turn + 1) % 2;
        }
    }

    static void readPlayers(String input) {
        String[] commands = input.split(" ");
        if (commands.length != 3) {
            throw new IllegalArgumentException();
        }
        if (!"start".equals(commands[0])) {
            throw new IllegalArgumentException();
        }
        players = new Player[2];
        players[0] = Player.of(commands[1]);
        players[1] = Player.of(commands[2]);
    }

    static boolean readCoordinates(boolean xPlayer) {
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
                TicTacToe.Result result = ticTacToe.set(row, column, xPlayer);
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

    static boolean aiTurn(boolean xPlayer) {
        String msg = switch (ticTacToe.autoGenWithEasyMode(xPlayer)) {
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
