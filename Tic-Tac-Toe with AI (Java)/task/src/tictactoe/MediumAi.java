package tictactoe;

public class MediumAi extends Player {

    public MediumAi(char sign) {
        this.sign = sign;
    }

    @Override
    void move(Board board) {
        System.out.println("Making move level \"medium\"");
        int bestRow = -1, bestCol = -1;
        char againstChar = sign == Board.PLAYER_X ? Board.PLAYER_O : Board.PLAYER_X;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!board.canMove(i, j)) {
                    continue;
                }
                if (board.canWin(i, j, sign)) {
                    board.move(i, j, sign);
                    return;
                }
                if (bestRow == -1 && board.canWin(i, j, againstChar)) {
                    bestRow = i;
                    bestCol = j;
                }
            }
        }
        // Can't win
        // Otherwise, random move
        if (bestRow == -1) {
            board.random(sign);
        } else {
            board.move(bestRow, bestCol, sign);
        }
    }
}
