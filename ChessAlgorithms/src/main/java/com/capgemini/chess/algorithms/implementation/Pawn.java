package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Pawn implements Movement {

	public boolean hasMoved;

	// Is the piece allowed to be taken via en passante?
	public boolean ep_able;

	public Pawn() {
		this.hasMoved = false;
	}

	@Override
	public boolean validatePieceMove(Coordinate from, Coordinate to, Color color, Board board)
			throws InvalidMoveException {

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
				if (board.getPieceAt(new Coordinate(from.getX(),from.getY() + 1)) != null) {
					return false;
				}
			} else {
				if (board.getPieceAt(new Coordinate(from.getX(),from.getY() - 1)) != null) {
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
					if (board.getPieceAt(new Coordinate( from.getX(),from.getY() + 2)) != null) {
						return false;
					}
				} else {
					if (board.getPieceAt(new Coordinate(from.getX(),from.getY() - 2)) != null) {
						return false;
					}
				}

				// En passante
				if (to.getX() + 1 < 8) {
					if (board.getPieceAt(new Coordinate(to.getX(), to.getX() + 1)) != null) {
						if (board.getPieceAt(new Coordinate(to.getX(), to.getX() + 1)).getClass()
								.isInstance(new Pawn())) {
							ep_able = true;
						}
					}
				} else if (to.getX() - 1 > 0) {
					if (board.getPieceAt(new Coordinate(to.getX(), to.getX() - 1)) != null) {
						if (board.getPieceAt(new Coordinate(to.getX(), to.getX() - 1)).getClass()
								.isInstance(new Pawn())) {
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
			if (board.getPieceAt(new Coordinate(to.getX(), to.getY())) == null) {
				return false;
			}
		}
		return true;

	}

	@Override
	public boolean isMoveBlocked(Coordinate from, Coordinate to) throws InvalidMoveException{
		// TODO Auto-generated method stub
		return false;
	}
}
