package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class PieceMoveFactory {

	ConditionMovement conditionMovement;
	private Coordinate from;
	private Board board;
	
	public PieceMoveFactory(ConditionMovement conditionMovement){
		this.conditionMovement=conditionMovement;
	}
	
	private PieceType type= board.getPieceAt(conditionMovement.getFrom()).getType();
	
	public Move getPiece() throws InvalidMoveException {
		switch(type){
		
		case BISHOP: {
			return conditionMovement.checkingValidationWithCondition(new Bishop());	
			}
		case PAWN: {
			return conditionMovement.checkingValidationWithCondition(new Bishop());
		}
		case KING: {
			return conditionMovement.checkingValidationWithCondition(new Bishop());
		}
		case QUEEN:{
			return conditionMovement.checkingValidationWithCondition(new Bishop());
		}
		case KNIGHT:{
			return conditionMovement.checkingValidationWithCondition(new Bishop());
		}
		case ROOK: {
			return conditionMovement.checkingValidationWithCondition(new Bishop());
		}
		}
		return null;
	}

	// return Move object 
	// sprawdza waruenk 
}
