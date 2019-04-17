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
import com.capgemini.chess.algorithms.implementation.exceptions.OpponentTurnException;
import com.capgemini.chess.algorithms.implementation.exceptions.PawnMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.QueenMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.RookMoveException;

public class ConditionMovement extends AbstractPieceMove {

	private Coordinate from;
	private Coordinate to;
	private Board board;
	private Color color;
	private Move currentMove = new Move();

	public ConditionMovement(Coordinate from, Coordinate to, Board board, Color color) { 
		this.from = from;
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
	 * @throws PawnMoveException
	 * @throws InvalidMoveException,
	 *             EmptySquareException, OpponentTurnException,
	 *             BishopMoveException, KnightMoveException, RookMoveException,
	 *             QueenMoveException, KingMoveException e
	 */
	public boolean checkingValidationWithCondition(Pieces pieces) {

		try {
			isSpotNotEmpty(from, board);
			isDestinationSpotNotEmpty(to, board);
			checkIfColorPieceBelongsToCurrentPlayer(board,from, to, color);
			isPiecePositionOnBoard(to, from, color, board, pieces);
			pieces.validateMove(board, from, to);

			return true;
		} catch (InvalidMoveException | EmptySquareException | BishopMoveException | KnightMoveException
				| RookMoveException | QueenMoveException | KingMoveException | PawnMoveException | OpponentTurnException e) {
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
	 * @throws QueenMoveException
	 * @throws RookMoveException
	 * @throws BishopMoveException
	 * @throws InvalidMoveException
	 * @throws IllegalStateException;
	 */
	public MoveType kindOfMoveType(Coordinate to, Coordinate from, Board board) throws InvalidMoveException {
		if(board.getPieceAt(from).getColor()==board.getPieceAt(to).getColor()){
			if (board.getPieces()[from.getX()][to.getY()] == null) {
				return MoveType.ATTACK;
			}
			if (board.getPieces()[from.getX()][to.getY()] != null) { 					
				return MoveType.CAPTURE;
			}
			throw new InvalidMoveException();
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
	 * @throws PawnMoveException
	 * @throws CannotMoveAtTurnException
	 */
	public Move playerCanMoveAtTurn(Pieces pieces, Coordinate from, Coordinate to, Board board) {
		try {
			if (checkingValidationWithCondition(pieces) == true) {
				currentMove.setFrom(from);
				currentMove.setTo(to);
				currentMove.setType(kindOfMoveType(to, from, board));
				currentMove.setMovedPiece(board.getPieces()[from.getX()][to.getX()]);
				return currentMove;
			}

		} catch (InvalidMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Coordinate getFrom() {
		return from;
	}
	public Board getBoard() {
		return board;
	}
}
