package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;

public class Bishop implements Movement {
	
	private Coordinate from;
	private Coordinate to;
	private MoveType type;
	private Piece movedPiece;
	//alt shift J - java doc
	//Move && Coordinate 
	
	 public Bishop() {
	}
	 
	 @Override
		public String validate(int from, int to) {
			// TODO Auto-generated method stub
			return null;
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
