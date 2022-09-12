package util;

import game.*;

public class Rook extends Queen{
    public Rook(boolean white) {
	super(white);
    }
    @Override
    public boolean isLegal(Move move, Game game) {
	if(!super.isLegal(move, game))
	    return false;

    int rowDiff = move.getRow1() - move.getRow0();
    int colDiff = move.getCol1() - move.getCol0();
    return rowDiff * colDiff == 0;
    }
    @Override
    public String toString() {
	return white?"\u2656":"\u265C";
    }
}
