package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.BishopMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.CannotMoveAtTurnException;
import com.capgemini.chess.algorithms.implementation.exceptions.EmptySquareException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KnightMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.NoKingAtTheBoardException;
import com.capgemini.chess.algorithms.implementation.exceptions.OpponentTurnException;
import com.capgemini.chess.algorithms.implementation.exceptions.QueenMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.RookMoveException;

public class ConditionMovement extends AbstractPieceMove{

	private Coordinate from;
	private Coordinate to;
	private Board board;
	private Color color;
	private Move currentMove = new Move();
	
	public ConditionMovement(Coordinate from, Coordinate to, Board board ,Color color) { // color
		this.from= from;
		this.to = to;
		this.board = board;
		this.color = color;
	}

	/**
	 * Checks to see if given condition is valid. This will be called every time
	 * after call out move with piece.
	 * 
	 * @param movement
	 *            Type of piece of 'movement' field
	 * @return boolean value corresponding to playerCanMoveAtTurn method
	 * @throws InvalidMoveException,
	 *             EmptySquareException, OpponentTurnException,
	 *             BishopMoveException, KnightMoveException, RookMoveException,
	 *             QueenMoveException, KingMoveException e
	 */
	public boolean checkingValidationWithCondition(Movement movement) {
		try {
			isNotEmpty(from);
			checkIfColorPieceBelongsToCurrentPlayer(board, from, to, color);
			isPiecePositionOnBoard(to, from, color, board);
			movement.isMoveBlocked(from, to);
			movement.validatePieceMove(from, to, color, board);

			return true;
		} catch (InvalidMoveException | EmptySquareException | OpponentTurnException | BishopMoveException
				| KnightMoveException | RookMoveException | QueenMoveException | KingMoveException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks to see kind of move type, every piece can do. This will be called
	 * twice after every move
	 * 
	 * @param to
	 *            coordinates of 'to' field
	 * @param from
	 *            coordinates of 'from' field
	 * @return MoveType value corresponding to
	 * @throws IllegalStateException;
	 */
	public MoveType kindOfMoveType(Coordinate to, Coordinate from) {
		if (board.getPieceAt(from).getColor() == board.getPieceAt(to).getColor()) {
			if (board.getPieceAt(to) == null) {
				return MoveType.ATTACK;
			}
			if (board.getPieceAt(to) != null) {
				return MoveType.CAPTURE;
			}
		} else {
			throw new IllegalStateException("Opponent turn");
		}
		return null;
	}

	/**
	 * Checks to see if the king of the given color is in check. This will be
	 * called twice after every move
	 * 
	 * @param movement
	 *            Type of piece of 'movement' field
	 * @return Move value corresponding to 
	 * @throws CannotMoveAtTurnException
	 */
	public Move playerCanMoveAtTurn(Movement movement) throws CannotMoveAtTurnException {
		if (checkingValidationWithCondition(movement) == true) {
			currentMove.setFrom(from);
			currentMove.setTo(to);
			currentMove.setType(kindOfMoveType(to, from));
			currentMove.setMovedPiece(board.getPieceAt(from));

			return currentMove;
		} else {
			throw new CannotMoveAtTurnException("Can't move right now!");
		}
	}

	public Coordinate getFrom() {
		return from;
	}

}
