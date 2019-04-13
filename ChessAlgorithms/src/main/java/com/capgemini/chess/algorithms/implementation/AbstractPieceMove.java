package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;

public class AbstractPieceMove{

	private Movement movement;
	private Board board;

	public AbstractPieceMove(Movement movement) {
		this.movement = movement;
	}

	public void checkingPiecePositionBoard(Coordinate to, Coordinate from) throws InvalidMoveException {
		if (from.getX() > board.SIZE || from.getY() > board.SIZE || from.getX() < 0 || from.getY() < 0
				|| to.getX() > board.SIZE || to.getY() > board.SIZE || to.getX() < 0 || to.getY() < 0) {

			throw new InvalidMoveException("Bishop out of the board"); // from
																		// to,
																		// na
																		// planszy?
		}
		if (from.getX() == to.getX() || from.getY() == to.getY()) {
			//return false; // nie poruszenie sie, miejsce poczatkowe
		} else {
			movement.validate(to, from);
		}
	}

	public void isKingInCheck() throws KingInCheckException {
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
	    
	    


