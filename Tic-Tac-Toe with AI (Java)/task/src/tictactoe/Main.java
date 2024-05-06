package tictactoe;

import java.util.Scanner;

public class Main {
    enum PlayerCommand {
        User, AI_Easy, AI_Medium, AI_Hard;

        static public Player of(String player, char sign) {
            PlayerCommand pc = null;
            for (PlayerCommand p : PlayerCommand.values()) {
                if (p.command().equals(player)) {
                    pc = p;
                    break;
                }
            }
            if (pc == null) {
                throw new IllegalArgumentException();
            }
            return switch (pc) {
                case User -> new User(sign);
                case AI_Easy -> new EasyAi(sign);
                case AI_Medium -> new MediumAi(sign);
                case AI_Hard -> new HardAi(sign);
            };
        }

        String command() {
            return switch (this) {
                case User -> "user";
                case AI_Easy -> "easy";
                case AI_Medium -> "medium";
                case AI_Hard -> "hard";
            };
        }
    }

    static Board board;
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
            System.out.print("Input command: ");
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
        board = new Board();
        board.print();
        int turn = 0;
        while (true) {
            players[turn].move(board);
            board.print();
            if (board.isWin(players[turn].sign)) {
                System.out.println(players[turn].sign + " wins");
                return false;
            } else if (board.isFull()) {
                System.out.println("Draw");
                return false;
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
        players[0] = PlayerCommand.of(commands[1], Board.PLAYER_X);
        players[1] = PlayerCommand.of(commands[2], Board.PLAYER_O);
    }
}
