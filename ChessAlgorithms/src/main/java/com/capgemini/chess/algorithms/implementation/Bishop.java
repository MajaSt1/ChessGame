package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Bishop implements Movement {

	private Coordinate from;
	private Coordinate to;
	private MoveType type;
	private Piece movedPiece;
	// alt shift J - java doc
	// Move && Coordinate

	public Bishop(Coordinate from, Coordinate to) {
		this.from = from;
		this.to= to;
	}

	@Override
	public boolean validate(Coordinate to , Coordinate from) {
		if (Math.abs(to.getX() - from.getX()) == Math.abs(from.getY() - to.getX())) {
			return true; // poruszanie sie po skosie dozwolone
		}
		return false;
	}

	public Coordinate getFrom() {
		return from;
	}

	public void setFrom(Coordinate from) {
		this.from = from;
	}

	public Coordinate getTo() {
		return to;
	}

	public void setTo(Coordinate to) {
		this.to = to;
	}

	public MoveType getType() {
		return type;
	}

	public void setType(MoveType type) {
		this.type = type;
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
