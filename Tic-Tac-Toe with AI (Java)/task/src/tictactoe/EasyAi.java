package tictactoe;

import java.util.Random;

public class EasyAi extends Player {

    public EasyAi(char sign) {
        this.sign = sign;
    }

    @Override
    void move(Board board) {
        System.out.println("Making move level \"easy\"");
        board.random(sign);
    }
}
