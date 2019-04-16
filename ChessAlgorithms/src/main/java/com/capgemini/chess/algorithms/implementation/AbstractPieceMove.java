package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.EmptySquareException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KnightMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.OpponentTurnException;
import com.capgemini.chess.algorithms.implementation.exceptions.QueenMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.RookMoveException;

public class AbstractPieceMove {

	protected Movement movement;
	private static Board board;

	/*
	 * public AbstractPieceMove(Movement movement) { this.movement = movement; }
	 */

	/**
	 * Checks to see if the piece is on board. This will be called every time
	 * after every condition with validation called
	 * 
	 * @param to
	 *            coordinates of 'to' field
	 * @param from
	 *            coordinates of 'from' field
	 * @param color
	 *            piece color of 'color' field
	 * @param board
	 *            board of 'board' field
	 * @throws InvalidMoveException,
	 *             KnightMoveException, RookMoveException, QueenMoveException,
	 *             KingMoveException
	 */
	public void isPiecePositionOnBoard(Coordinate to, Coordinate from, Color color, Board board)
			throws InvalidMoveException, KnightMoveException, RookMoveException, QueenMoveException, KingMoveException { // void
		if (from.getX() > board.SIZE || from.getY() > board.SIZE || from.getX() < 0 || from.getY() < 0
				|| to.getX() > board.SIZE || to.getY() > board.SIZE || to.getX() < 0 || to.getY() < 0) {

			throw new InvalidMoveException("Piece out of the board"); // from
																		// to,na
																		// planszy?
		}
		if (from.getX() == to.getX() || from.getY() == to.getY()) {
			throw new InvalidMoveException("Piece had not changed position"); // nie
																				// poruszenie
																				// sie,
																				// miejsce
																				// poczatkowe
		} else {
			movement.validatePieceMove(to, from, color, board);
		}
	}

	/**
	 * Checks to see if position at the board is not empty.This will be called every time
	 * after every condition with validation called
	 * 
	 * @param from
	 *            coordinate of 'from' field
	 * @throws EmptySquareException
	 */
	public void isNotEmpty(Coordinate from) throws EmptySquareException {
		if (board.getPieceAt(from) != null) {
			return;
		}
		throw new EmptySquareException();
	}

	/**
	 * Checks to see if piece of given color belongs to current player.This will be called every time
	 * after every condition with validation called
	 * 
	 * @param to
	 *            coordinates of 'to' field
	 * @param from
	 *            coordinates of 'from' field
	 * @param color
	 *            piece color of 'color' field
	 * @param board
	 *            board of 'board' field
	 * @throws OpponentTurnException
	 */
	public void checkIfColorPieceBelongsToCurrentPlayer(Board board, Coordinate from, Coordinate to, Color color)
			throws OpponentTurnException {
		if (board.getPieceAt(from).getColor() == color) {
			return;
		} else {
			throw new OpponentTurnException("Opponent turn");
		}
	}
}
