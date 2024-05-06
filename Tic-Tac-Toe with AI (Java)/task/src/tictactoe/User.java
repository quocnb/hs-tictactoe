package tictactoe;

import java.util.Scanner;

public class User extends Player {

    public User(char sign) {
        this.sign = sign;
    }

    @Override
    void move(Board board) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine();
            if (!input.matches("\\d+ \\d+")) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int row = Integer.parseInt(input.split(" ")[0]) - 1;
            int column = Integer.parseInt(input.split(" ")[1]) - 1;
            if (row < 0 || row > 2 || column < 0 || column > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (!board.canMove(row, column)) {
                System.out.println("This cell is occupied! Choose another one!");
            }
            board.move(row, column, sign);
            break;
        }
    }
}
