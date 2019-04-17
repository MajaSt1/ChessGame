package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.BishopMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.EmptySquareException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KnightMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.OpponentTurnException;
import com.capgemini.chess.algorithms.implementation.exceptions.PawnMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.QueenMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.RookMoveException;

public class AbstractPieceMove {

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
	 * @throws PawnMoveException
	 * @throws BishopMoveException
	 */
	public void isPiecePositionOnBoard(Coordinate to, Coordinate from, Color color, Board board, Pieces pieces)
			throws InvalidMoveException, KnightMoveException, RookMoveException, QueenMoveException, KingMoveException,
			BishopMoveException, PawnMoveException { // void
		if (from.getX() > 7 || from.getY() > 7 || from.getX() < 0 || from.getY() < 0 || to.getX() > 7 || to.getY() > 7
				|| to.getX() < 0 || to.getY() < 0) {

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
			pieces.validateMove(board, from, to);
		}
	}

	/**
	 * Checks to see if position at the board is not empty.This will be called
	 * every time after every condition with validation called
	 * 
	 * @param from
	 *            coordinate of 'from' field
	 * @throws EmptySquareException
	 */
	public void isNotEmpty(Coordinate from, Board board) throws EmptySquareException {
		if (board.getPieceAt(from) != null) {
			return;
		}
		throw new EmptySquareException();
	}

	/**
	 * Checks to see if piece of given color belongs to current player.This will
	 * be called every time after every condition with validation called
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
