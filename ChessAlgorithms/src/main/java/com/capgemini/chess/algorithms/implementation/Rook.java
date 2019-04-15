package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Rook implements Movement {

	@Override
	public boolean validate(Coordinate from, Coordinate to) throws InvalidMoveException {
		if(from.getY() == to.getY() || from.getX() == to.getX()){
		return false;}
		else {
			throw new InvalidMoveException("This kind of move is not valid!");
		}
	}

	@Override
	public boolean isMoveBlocked(Coordinate from, Coordinate to) throws InvalidMoveException {
		for (int i = 1; i < Math.abs(to.getX() - from.getX()) - 1; ++i) {
		return false;
	}
		return false;
	}

}
