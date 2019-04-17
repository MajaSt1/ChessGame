package com.capgemini.chess.algorithms.implementation;

import java.text.Normalizer.Form;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.KingMoveException;

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
	public boolean validateMove(Board board, Coordinate from, Coordinate to) throws KingMoveException {

		int absoluteX = Math.abs(to.getX() - from.getX());
		int absoluteY = Math.abs(to.getY() - from.getY());
		
		if (absoluteX <= 1 && absoluteY <= 1){
			if (absoluteX == 0 && absoluteY == 0){
				throw new KingMoveException();
			}
			return true;
		}
		throw new KingMoveException();
	}
			// castle
		/*	if (to.getX() - from.getX() == 2 && from.getY() == to.getY()) {
				if (board.getPieces()[from.getX() + 1][to.getY()] != null
						|| board.getPieces()[from.getX() + 2][to.getY()] != null) {
					castled = false;
					throw new KingMoveException();
				}
			} else if (from.getX() - to.getX() == 3 && from.getY() == to.getY()) {
				if (board.getPieces()[from.getX() - 1][to.getY()] != null
						|| board.getPieces()[from.getX() - 2][to.getY()] != null || board.getPieces()[from.getX() - 3][to.getY()] != null) {
					castled = false;
					throw new KingMoveException();
				}
			} else {
				castled = false;
				throw new KingMoveException();
			}
			castled = true; */

		
		//hasMoved = true;
	
}
