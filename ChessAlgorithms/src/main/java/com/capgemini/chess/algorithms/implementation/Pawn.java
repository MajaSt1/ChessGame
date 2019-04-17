package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.PawnMoveException;

public class Pawn extends Pieces {

	public boolean hasMoved;
	public Color color;

	// Is the piece allowed to be taken via en passante?
	public boolean ep_able;

	public Pawn(Color color) {
		this.color = color;
		this.hasMoved = false;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public boolean validateMove(Board board, Coordinate from, Coordinate to) throws PawnMoveException {

		if (color.equals(Color.WHITE)) {
			if (from.getY() > to.getY()) {
				return false;
			}
		} else {
			if (to.getY() > from.getY()) {
				return false;
			}

		}

		if (from.getX() == to.getX()) {
			// not taking a piece
			if (color.equals(Color.WHITE)) {
				if (board.getPieces()[from.getY() + 1][from.getX()] != null) {
					return false;
				}
			} else {
				if (board.getPieces()[from.getY() - 1][from.getX()] != null) {
					return false;
				}
			}
			if (Math.abs(to.getY() - from.getY()) > 2) {
				return false;
			} else if (Math.abs(to.getY() - from.getY()) == 2) {
				if (hasMoved) {
					return false; //
				}

				if (color.equals(Color.WHITE)) {
					if (board.getPieces()[from.getY() + 2][from.getX()] != null) {
						return false;
					}
				} else {
					if (board.getPieces()[from.getY() - 2][from.getX()] != null) {
						return false;
					}
				}

				// En passante
				if (to.getX() + 1 < 8) {
					if (board.getPieces()[from.getY() + 1][from.getX()] != null) {
						if (board.getPieces()[to.getY()][to.getY() + 1].getClass().isInstance(new Pawn(Color.WHITE))) {
							ep_able = true;
						}
					}
				} else if (to.getX() - 1 > 0) {
					if (board.getPieces()[from.getY() - 1][from.getX()] != null) {
						if (board.getPieces()[to.getY()][to.getY() - 1].getClass().isInstance(new Pawn(Color.WHITE))) {
							ep_able = true;
						}
					}
				}
			}
		} else {
			// Taking a piece
			if (Math.abs(to.getX() - from.getX()) != 1 || Math.abs(to.getY() - from.getY()) != 1) {
				return false;
			}
			if (board.getPieces()[to.getY()][to.getX()] == null) {
				return false;
			}
		}
		return true;
	}
}
