package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.CannotMoveAtTurnException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class PieceMoveFactory {

	ConditionMovement conditionMovement;
	private Coordinate from;
	private Board board;
	
	public PieceMoveFactory(ConditionMovement conditionMovement){
		this.conditionMovement=conditionMovement;
	}
	
	private PieceType type= board.getPieceAt(conditionMovement.getFrom()).getType();
	
	public Move getPiece() throws InvalidMoveException, CannotMoveAtTurnException {
		switch(type){
		
		case BISHOP: {
			return conditionMovement.playerCanMoveAtTurn(new Bishop());	
			}
		case PAWN: {
			return conditionMovement.playerCanMoveAtTurn(new Pawn());
		}
		case KING: {
			return conditionMovement.playerCanMoveAtTurn(new King());
		}
		case QUEEN:{
			return conditionMovement.playerCanMoveAtTurn(new Queen());
		}
		case KNIGHT:{
			return conditionMovement.playerCanMoveAtTurn(new Knight());
		}
		case ROOK: {
			return conditionMovement.playerCanMoveAtTurn(new Rook());
		}
		}
		return null;
	}

	// return Move object 
	// sprawdza waruenk 
}
