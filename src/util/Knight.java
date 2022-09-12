package util;

import game.*;

public class Knight extends Piece{
    public Knight(boolean white) {
	super(white);
    }
    @Override
    public boolean isLegal(Move move, Game game) {
        if(!super.isLegal(move, game))
            return false;
        int rowDiff = move.getRow1() - move.getRow0();
        int colDiff = move.getCol1() - move.getCol0();
        return Math.abs(rowDiff * colDiff) == 2;
    }
    @Override
    public String toString() {
	return white?"\u2658":"\u265E";
    }
}
