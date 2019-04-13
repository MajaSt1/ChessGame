package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class PieceMoveFactory {

	AbstractPieceMove abstractPieceMove;

	public Move getPiece(PieceType piece) {
		switch(piece){
		
		case BISHOP: {
			return new AbstractPieceMove(new Bishop());	
			};
		}
		return null;
	}

	// return Move object 
	// sprawdza waruenk 
}
