package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.BishopMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.QueenMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.RookMoveException;

public class Queen extends Pieces {
	public Color color;

	public Queen(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public boolean validateMove(Board board, Coordinate from, Coordinate to)
			throws QueenMoveException, RookMoveException, BishopMoveException {

		Rook rook = new Rook(color);
		Bishop bishop=new Bishop(color);
		
		return rook.validateMove(board, from, to) || bishop.validateMove(board, from, to);
	}
}
