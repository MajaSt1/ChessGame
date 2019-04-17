package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.BishopMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.EmptySquareException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;
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

			throw new InvalidMoveException("Piece out of the board"); 
		}
		if (from.getX() == to.getX() || from.getY() == to.getY()) {
			throw new InvalidMoveException("Piece had not changed position"); 
			
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
	public void isSpotNotEmpty(Coordinate from, Board board) throws EmptySquareException {
		if (board.getPieceAt(from) != null) {
			return;
		}
		throw new EmptySquareException();
	}

	public void isDestinationSpotNotEmpty(Coordinate to, Board board) throws EmptySquareException {
		if (board.getPieceAt(to) != null) {
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
	 * @throws KingInCheckException 
	 * @throws OpponentTurnException
	 */
	public boolean isKingInCheckValidator(Color kingColor, Board board) throws KingInCheckException {
		Board tempBoard = new Board(); // kopia

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Coordinate spot = new Coordinate(i, j);
				tempBoard.setPieceAt(board.getPieceAt(spot), spot); // kopia
																	// tablicy
																	// figurami
			}
		}
		Coordinate kingPosition = findMyKingPosition(kingColor, tempBoard); // koordynaty
																			// krola
		if (kingPosition != null) { // sprawdzamy czy nie zwrocilo null
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) { // wykonanie iteracji jeszcze raz
												// po tablicy
					Coordinate spot = new Coordinate(i, j); // sprawdzamy czy
															// jest check
					if (tempBoard.getPieceAt(spot) != null && tempBoard.getPieceAt(spot).getColor() != kingColor) { // inny
																													// kolo-
																													// przeciwnik
						if (canPieceReachKing(spot, kingPosition, tempBoard, kingColor)) {
							
							throw new KingInCheckException();
						}
					}
				}
			}
		}
		return false;
	}

	// spot- miejsce figury przeciwnika
	private boolean canPieceReachKing(Coordinate spot, Coordinate kingPosition, Board tempBoard, Color color) {
		boolean kingReached = false; // spot - from ; kingPosition - to
		try {
			ConditionMovement conditionMovement = new ConditionMovement(spot, kingPosition, tempBoard, color);
			PiecesMoveFactory piecesMoveFactory = new PiecesMoveFactory(conditionMovement, spot, kingPosition,
					tempBoard, color);
			piecesMoveFactory.getPiece();
			kingReached = true;
		} catch (InvalidMoveException e2) {
		}
		return kingReached;
	}

	private Coordinate findMyKingPosition(Color myColor, Board tempBoard) {
		for (int i = 0; i < 8; i++) { // iteracja po kopii
			for (int j = 0; j < 8; j++) {
				Coordinate spot = new Coordinate(i, j); // pozycje z kopii
				if (tempBoard.getPieceAt(spot) != null && tempBoard.getPieceAt(spot).getType() == PieceType.KING
						&& tempBoard.getPieceAt(spot).getColor() == myColor) {
					return spot; // zwraca koordynat , pozycje
				}
			}
		}
		return null;
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
	 * @throws KingInCheckException 
	 * @throws OpponentTurnException
	 */

public boolean isAnyMoveValidator(Color nextMoveColor, Board board) {
	Coordinate fromSpot, toSpot;
	boolean foundPossibleMove = false;
	for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				fromSpot = new Coordinate(i, j);
				if (board.getPieceAt(fromSpot) != null && (board.getPieceAt(fromSpot).getColor() == nextMoveColor)) {

					for (int m = 0; m < 8; m++) {
						for (int n = 0; n < 8; n++) {
							toSpot = new Coordinate(m, n);
							try {
								ConditionMovement conditionMovement = new ConditionMovement(fromSpot, toSpot,
										board, nextMoveColor);
								PiecesMoveFactory piecesMoveFactory = new PiecesMoveFactory(conditionMovement, fromSpot,
										toSpot, board, nextMoveColor);
								piecesMoveFactory.getPiece();
							} catch (InvalidMoveException e2) {
							}
						}
					}
			}
		}
	}
	return foundPossibleMove; 
	
}}

