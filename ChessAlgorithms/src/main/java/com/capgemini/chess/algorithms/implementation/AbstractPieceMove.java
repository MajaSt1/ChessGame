package com.capgemini.chess.algorithms.implementation;

import org.junit.experimental.theories.FromDataPoints;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;

public class AbstractPieceMove{

	protected Movement movement;
	private static Board board;

/*	public AbstractPieceMove(Movement movement) {
		this.movement = movement;
	} */

	public boolean isPiecePositionOnBoard(Coordinate to, Coordinate from) throws InvalidMoveException {
		if (from.getX() > board.SIZE || from.getY() > board.SIZE || from.getX() < 0 || from.getY() < 0
				|| to.getX() > board.SIZE || to.getY() > board.SIZE || to.getX() < 0 || to.getY() < 0) {

			throw new InvalidMoveException("Piece out of the board"); // from to,na planszy?
		}
		if (from.getX() == to.getX() || from.getY() == to.getY()) {
			throw new InvalidMoveException("Piece had not changed position"); // nie poruszenie sie, miejsce poczatkowe
		} else {
			movement.validate(to, from);
		}
		return false;
	}

	public boolean isNotEmpty(Coordinate from) {
		if (board.getPieceAt(from) != null) {
			return true;
		}
		return false;
	}
	
	public MoveType kindOfMoveType (Coordinate to, Coordinate from){
		if(board.getPieceAt(from).getColor() == board.getPieceAt(to).getColor()){
			if(board.getPieceAt(to)== null){
				return MoveType.ATTACK;
			} if(board.getPieceAt(to)!= null){
				return MoveType.CAPTURE;
			}
			
		}else {
			throw new IllegalStateException("Opponent turn");
		}
		return null;
	}
	

	public boolean isKingInCheck() throws KingInCheckException {
		throw new KingInCheckException();
	}

}

         
	    
	// czy ti jest nasz pion
	// odslona krola- king in check (po wykonanym ruchu) 
	// czy from ma nasza figure 
	// opisywac exceptiony = konkretnie 
	/*
	 * */
	 
	    
	  //  InvalidMoveException, KingInCheckException
	    
	    


