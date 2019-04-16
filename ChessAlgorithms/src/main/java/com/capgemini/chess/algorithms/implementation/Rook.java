package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.RookMoveException;

public class Rook implements Movement {
	
	private Board board;

	@Override
	public boolean validatePieceMove(Coordinate from, Coordinate to, Color color, Board board) throws RookMoveException  {
		if (from.getY() == to.getY() || from.getX() == to.getX()) {
			return true;
		} else {
			throw new RookMoveException();
		}
	}

	@Override
	public boolean isMoveBlocked(Coordinate from, Coordinate to) throws RookMoveException {
		if (from.getX() == to.getX()) { // horizontal move
			if (from.getY() < to.getY())
			// move right
			{
				for (int i = from.getY() + 1; i <= to.getY(); i++) {
					Coordinate pathCoord = new Coordinate(from.getX(), i);
					if (board.getPieceAt(pathCoord) != null) {
						throw new RookMoveException();
					}
				}
			} else { // move left
				for (int i = from.getY() - 1; i >= to.getY(); --i) {
					Coordinate pathCoord = new Coordinate(from.getX(), i);
					if (board.getPieceAt(pathCoord) != null) {
						throw new RookMoveException();
					}
				}
			}
		} else if (from.getY() == to.getY()) { // vertical case
			if (from.getX() < to.getX()) { // Move down
				for (int i = from.getX() + 1; i <= to.getX(); ++i) {
					Coordinate pathCoord = new Coordinate(from.getY(), i);
					if (board.getPieceAt(pathCoord) != null) {
						throw new RookMoveException();
					}
				}
			} // Move up
			else {
				for (int i = from.getX() - 1; i >= to.getX(); --i) {
					Coordinate pathCoord = new Coordinate(from.getY(), i);
					if (board.getPieceAt(pathCoord) != null) {
						throw new RookMoveException();
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

}

