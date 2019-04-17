package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.BishopMoveException;

public class Bishop extends Pieces {

	public Color color;

	public Bishop(Color color) {
		this.color = color;
	}

	public boolean validateMove(Board board, Coordinate from, Coordinate to) throws BishopMoveException {

		if (from.getY() == to.getY() || from.getX() == to.getX()) {
			throw new BishopMoveException();
		}

		if (Math.abs(to.getY() - from.getY()) != Math.abs(to.getX() - from.getX())) {
			throw new BishopMoveException();
		}

		int row;
		int col;

		if (from.getY() < to.getY()) {
			row = 1;
		} else {
			row = -1;
		}

		if (from.getX() < to.getX()) {
			col = 1;
		} else {
			col = -1;
		}

		int y = from.getX() + col;
		for (int x = from.getY() + row; x != to.getY(); x += row) {

			if (board.getPieces()[x][y] != null) {
			throw new BishopMoveException();
			}

			y += col;
		}

		return true;
	}

	public Color getColor() {
		return this.color;
	}
}
