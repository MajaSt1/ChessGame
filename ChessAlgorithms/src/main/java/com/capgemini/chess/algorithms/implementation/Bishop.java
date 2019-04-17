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

		int directionX = to.getX() < from.getX() ? 1 : -1;
		int directionY = to.getY() < from.getY() ? 1 : -1;

		if (Math.abs(to.getX() - from.getX()) == Math.abs(from.getY() - to.getX())) {
			return true; // poruszanie sie po skosie dozwolone
		} else {
			for (int i = 1; i < Math.abs(to.getX() - from.getX()) - 1; ++i) {

				if (pieceOnSquare(board, from.getX() + i * directionX, from.getY() + i * directionY)) {
					throw new BishopMoveException();
				}
			}
			throw new BishopMoveException();
		}
	}

	private boolean pieceOnSquare(Board board, int i, int j) {
		if (board.getPieces()[i][j] != null) {
			return true;
		}
		return false;
	}

	public Color getColor() {
		return this.color;
	}
}
