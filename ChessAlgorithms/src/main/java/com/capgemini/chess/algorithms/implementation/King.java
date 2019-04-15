package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class King implements Movement{

	@Override
	public boolean validate(Coordinate from, Coordinate to) throws InvalidMoveException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMoveBlocked(Coordinate from, Coordinate to) throws InvalidMoveException {
		// TODO Auto-generated method stub
		return false;
	}

}
