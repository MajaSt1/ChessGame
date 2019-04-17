package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.KnightMoveException;

public class Knight extends Pieces {

	public Color color;

	public Knight(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor() {

		return color;
	}

	@Override
	public boolean validateMove(Board board, Coordinate from, Coordinate to) throws KnightMoveException {
		if (Math.abs(from.getX() - to.getX()) == 2 && Math.abs(from.getY() - to.getY()) == 1)
			return true;
		if (Math.abs(from.getX() - to.getX()) == 1 && Math.abs(from.getY() - to.getY()) == 2)
			return true;
		throw new KnightMoveException();
		
		/*
		int equation = (int) (Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
		if (equation == 5) {
			return true;
		} else {
			throw new KnightMoveException();
		} */
	}
}
