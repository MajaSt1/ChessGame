package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.BishopMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KnightMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.PawnMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.QueenMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.RookMoveException;

public abstract class Pieces {

	public String color;
	public boolean hasMoved;
	public boolean ep_able;

	public abstract Color getColor();

	public abstract boolean validateMove(Board board, Coordinate from, Coordinate to)
			throws BishopMoveException, KnightMoveException, PawnMoveException, QueenMoveException, RookMoveException;
}
