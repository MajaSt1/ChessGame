package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

public class King extends Pieces {

	public Color color;
	public boolean hasMoved;
	public boolean castled;

	public King(Color color) {
		this.color = color;
		this.hasMoved = false;
		this.castled = false;
	}

	public Color getColor() {
		return this.color;
	}

	@Override
	public boolean validateMove(Board board, Coordinate from, Coordinate to) {

		if (Math.abs(to.getY() - from.getY()) > 1 || Math.abs(to.getX() - from.getX()) > 1) {
			if (hasMoved) {
				return false;
			}
			// castle
			if (to.getX() - from.getX() == 2 && from.getY() == to.getY()) {
				//
				if (board.getPieces()[to.getY()][from.getX() + 1] != null
						|| board.getPieces()[to.getX()][from.getX() + 2] != null) {
					castled = false;
					return false;
				}
			} else if (from.getX() - to.getX() == 3 && from.getY() == to.getY()) {
				if (board.getPieces()[to.getY()][from.getX() - 1] != null
						|| board.getPieces()[to.getX()][from.getX() - 2] != null) {
					castled = false;
					return false;
				}
			} else {
				castled = false;
				return false;
			}
			castled = true;

		}
		hasMoved = true;
		return true;
	}
	
	public void kingPosition(){
		
	}
}
