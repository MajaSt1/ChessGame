package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingMoveException;

public class King implements Movement{

	@Override
	public boolean validatePieceMove(Coordinate from, Coordinate to, Color color, Board board) throws KingMoveException {
		int absoluteX = Math.abs(to.getX() - from.getX());
		int absoluteY = Math.abs(to.getY() - from.getY());
		
		if (absoluteX <= 1 && absoluteY <= 1){
			if (absoluteX == 0 && absoluteY == 0){
				return false;
			}
			return true;
		}
		throw new KingMoveException();
	}

	@Override
	public boolean isMoveBlocked(Coordinate from, Coordinate to) throws InvalidMoveException {
		return false;
	
	}

}
