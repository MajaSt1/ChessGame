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

		return (new Rook(color).validateMove(board, from, to) || new Bishop(color).validateMove(board, from, to));
	}
}
