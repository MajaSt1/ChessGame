package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Bishop implements Movement {
	
	private Piece movedPiece;
	private Board board;
	// alt shift J - java doc
	// Move && Coordinate

	@Override
	public boolean validate(Coordinate from , Coordinate to) throws InvalidMoveException {
		// 
		if (Math.abs(to.getX() - from.getX()) == Math.abs(from.getY() - to.getX())) {
			return true; // poruszanie sie po skosie dozwolone
		}else{
			throw new InvalidMoveException("This kind of move is not valid!");
		}
	}
	
	@Override
	public boolean isMoveBlocked(Coordinate from, Coordinate to) throws InvalidMoveException {

		int directionX = to.getX() < from.getX() ? 1 : -1;
		int directionY = to.getY() < from.getY() ? 1 : -1;
		for (int i = 1; i < Math.abs(to.getX() - from.getX()) - 1; ++i) {
			if (pieceOnSquare(from.getX() + i * directionX, from.getY() + i * directionY)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean pieceOnSquare(int i, int j){
		return true;
		
	}
	
	public Piece getMovedPiece() {
		return movedPiece;
	}

	public void setMovedPiece(Piece movedPiece) {
		this.movedPiece = movedPiece;
	}

	// przedstawienie ruchow 
	//sciezka

}
