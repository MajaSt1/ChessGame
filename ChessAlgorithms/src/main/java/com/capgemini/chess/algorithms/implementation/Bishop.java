package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.BishopMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Bishop implements Movement {
	
	private Piece movedPiece;
	private Board board;
	private Coordinate coordinate;
	// alt shift J - java doc
	// Move && Coordinate

	@Override
	public boolean validatePieceMove(Coordinate from, Coordinate to, Color color, Board board) throws InvalidMoveException { // validate move direction
		// 
		if (Math.abs(to.getX() - from.getX()) == Math.abs(from.getY() - to.getX())) {
			return true; // poruszanie sie po skosie dozwolone
		}else{
			throw new InvalidMoveException("This kind of move is not valid!");
		}
	}
	
	@Override
	public boolean isMoveBlocked(Coordinate from, Coordinate to) throws BishopMoveException {

		int directionX = to.getX() < from.getX() ? 1 : -1;
		int directionY = to.getY() < from.getY() ? 1 : -1;
		for (int i = 1; i < Math.abs(to.getX() - from.getX()) - 1; ++i) {
			if (pieceOnSquare(from.getX() + i * directionX, from.getY() + i * directionY)) {
				throw new BishopMoveException();
			}
		}
		return true;
	}

	private boolean pieceOnSquare(int i, int j) {
		if (board.getPieces()[i][j] != null) {
			return true;
		}
		return false;
	}
	
	public Piece getMovedPiece() {
		return movedPiece;
	}

	public void setMovedPiece(Piece movedPiece) {
		this.movedPiece = movedPiece;
	}
}
