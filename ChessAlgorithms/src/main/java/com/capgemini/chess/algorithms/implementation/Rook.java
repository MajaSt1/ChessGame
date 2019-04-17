package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.RookMoveException;

public class Rook extends Pieces {

	public Color color;

	public Rook(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public boolean validateMove(Board board, Coordinate from, Coordinate to) throws RookMoveException {
		if (from.getY() == to.getY() || from.getX() == to.getX()) {
			return true;
		} else if (from.getX() == to.getX()) { // horizontal move
			if (from.getY() < to.getY())
			// move right
			{
				for (int i = from.getY() + 1; i <= to.getY(); i++) {
					if (board.getPieces()[from.getX()][i] != null) {
						throw new RookMoveException();
					}
				}
			} else { // move left
				for (int i = from.getY() - 1; i >= to.getY(); --i) {
					if (board.getPieces()[from.getX()][i] != null) {
						throw new RookMoveException();
					}
				}
			}
		} else if (from.getY() == to.getY()) { // vertical case
			if (from.getX() < to.getX()) { // Move down
				for (int i = from.getX() + 1; i <= to.getX(); ++i) {
					if (board.getPieces()[from.getY()][i] != null) {
						throw new RookMoveException();
					}
				}
			} // Move up
			else {
				for (int i = from.getX() - 1; i >= to.getX(); --i) {
					if (board.getPieces()[from.getY()][i] != null) {
						throw new RookMoveException();
					}
				}
			}
		} else {
			throw new RookMoveException();
		}
		return true;
	}

}
