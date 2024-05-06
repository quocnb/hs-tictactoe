package tictactoe;

public class HardAi extends Player {
    public HardAi(char sign) {
        this.sign = sign;
    }

    @Override
    void move(Board board) {
        int bestRow = -1;
        int bestCol = -1;
        int bestScore = sign == Board.PLAYER_X ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.canMove(i, j)) {
                    board.move(i, j, sign);
                    if (sign == Board.PLAYER_X) {
                        int moveScore = minimax(board, 5, false);
                        if (moveScore > bestScore) {
                            bestScore = moveScore;
                            bestRow = i;
                            bestCol = j;
                        }
                    } else {
                        int moveScore = minimax(board, 5, true);
                        if (moveScore < bestScore) {
                            bestScore = moveScore;
                            bestRow = i;
                            bestCol = j;
                        }
                    }
                    board.reset(i, j);

                }
            }
        }
        board.move(bestRow, bestCol, sign);
    }

    private static int evaluate(Board board) {
        if (board.isWin(Board.PLAYER_X)) {
            return 10;
        } else if (board.isWin(Board.PLAYER_O)) {
            return -10;
        } else {
            return 0;
        }
    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        int score = evaluate(board);
        if (score == 10 || score == -10 || board.isFull() || depth == 0) {
            return score;
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.canMove(i, j)) {
                        board.move(i, j, Board.PLAYER_X);
                        bestScore = Math.max(bestScore, minimax(board, depth - 1, false));
                        board.reset(i, j);
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.canMove(i, j)) {
                        board.move(i, j, Board.PLAYER_O);
                        bestScore = Math.min(bestScore, minimax(board, depth - 1, true));
                        board.reset(i, j);
                    }
                }
            }
        }
        return bestScore;
    }
}
