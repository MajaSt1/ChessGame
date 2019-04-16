package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KnightMoveException;

public class Knight implements Movement{

	@Override
	public boolean validatePieceMove(Coordinate from, Coordinate to, Color color, Board board) throws KnightMoveException {
	     int equation = (int) (Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
	        if (equation == 5) {
	            return true;
	        } else {
	        	throw new KnightMoveException();
	        }
	}

	@Override
	public boolean isMoveBlocked(Coordinate from, Coordinate to) throws InvalidMoveException {
		// TODO Auto-generated method stub
		return false;
	}

}
