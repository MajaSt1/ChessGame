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

	public boolean checkingValidationWithCondition(Movement movement)
			throws InvalidMoveException, EmptySquareException, OpponentTurnException {
		
		try {
			isNotEmpty(from);
			checkIfColorPieceBelongsToCurrentPlayer(board, from, to, color);
			isPiecePositionOnBoard(to, from);
			movement.isMoveBlocked(from, to);
			movement.validatePieceMove(from, to, color, board);
			
			return true;
		} catch (InvalidMoveException | EmptySquareException | OpponentTurnException | BishopMoveException | KnightMoveException | RookMoveException | QueenMoveException | KingMoveException e) {
			e.printStackTrace();
			return false;
		}
	/*	try {
			isNotEmpty(from);
		} catch (Exception e) {
			throw new EmptySquareException();
		}
		try {
			checkIfColorPieceBelongsToCurrentPlayer(board, from, to, color);
		} catch (Exception e) {
			throw new OpponentTurnException();
		}
		try {
			isPiecePositionOnBoard(to, from);
		} catch (Exception e) {
			throw new IllegalStateException();
		}
		try {
			movement.isMoveBlocked(from, to);
			movement.validatePieceMove(from, to);
		} catch (Exception e) {
			throw new InvalidMoveException();
		}
		return false; */
	}
	
	public MoveType kindOfMoveType(Coordinate to, Coordinate from) {
		if (board.getPieceAt(from).getColor() == board.getPieceAt(to).getColor()){
		if (board.getPieceAt(to) == null) {
			return MoveType.ATTACK;
		}
			if (board.getPieceAt(to) != null) {
				return MoveType.CAPTURE;
			}
		}
		else {
			throw new IllegalStateException("Opponent turn");
		}
		return null;
	}

	public Move playerCanMoveAtTurn(Movement movement) throws CannotMoveAtTurnException{
		
		try {
			if(checkingValidationWithCondition(movement)==true){
				currentMove.setFrom(from);
				currentMove.setTo(to);
				currentMove.setType(kindOfMoveType(to, from));
				currentMove.setMovedPiece(board.getPieceAt(from));
				
				return currentMove;
			}else {
			throw new CannotMoveAtTurnException("Can't move right now!");
			}
		} catch (InvalidMoveException | EmptySquareException | OpponentTurnException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public Coordinate getFrom() {
		return from;
	}

}
