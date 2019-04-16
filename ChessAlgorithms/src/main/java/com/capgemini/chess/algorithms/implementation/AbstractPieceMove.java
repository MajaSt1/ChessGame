package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.EmptySquareException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;
import com.capgemini.chess.algorithms.implementation.exceptions.KnightMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.OpponentTurnException;

public class AbstractPieceMove{

	protected Movement movement;
	private static Board board;
	//private Color color;

/*	public AbstractPieceMove(Movement movement) {
		this.movement = movement;
	} */

	public void isPiecePositionOnBoard(Coordinate to, Coordinate from) throws InvalidMoveException, KnightMoveException { // void
		if (from.getX() > board.SIZE || from.getY() > board.SIZE || from.getX() < 0 || from.getY() < 0
				|| to.getX() > board.SIZE || to.getY() > board.SIZE || to.getX() < 0 || to.getY() < 0) {

			throw new InvalidMoveException("Piece out of the board"); // from to,na planszy?
		}
		if (from.getX() == to.getX() || from.getY() == to.getY()) {
			throw new InvalidMoveException("Piece had not changed position"); // nie poruszenie sie, miejsce poczatkowe
		} else {
			movement.validatePieceMove(to, from);
		}
	} 

	public void isNotEmpty(Coordinate from) throws EmptySquareException {
		if (board.getPieceAt(from) != null) {
			return;
		}
		throw new EmptySquareException();
	}
	
 	public void checkIfColorPieceBelongsToCurrentPlayer(Board board, Coordinate from, Coordinate to, Color color) throws OpponentTurnException{
 		if(board.getPieceAt(from).getColor()==color){
 			return;
 		} else {
 				throw new OpponentTurnException("Opponent turn");
 			}
 	}
 	
	public boolean isKingInCheck() throws KingInCheckException {
		throw new KingInCheckException();
		// iteracja king
	}
}    
	// czy ti jest nasz pion
	// odslona krola- king in check (po wykonanym ruchu) 
	// czy from ma nasza figure 
	// opisywac exceptiony = konkretnie
	    
	    


