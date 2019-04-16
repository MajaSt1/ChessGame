package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.QueenMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.RookMoveException;

public class Queen implements Movement{

	private Bishop bishop;
	private Rook rook;
	
	@Override
	public boolean validatePieceMove(Coordinate from, Coordinate to, Color color, Board board) throws QueenMoveException, InvalidMoveException, RookMoveException {
		if(bishop.validatePieceMove(from, to, color, board) || rook.validatePieceMove(from, to, color, board) ){
			return true;
		}else {
			throw new QueenMoveException();
		}
	}

	@Override
	public boolean isMoveBlocked(Coordinate from, Coordinate to) throws InvalidMoveException{
		// TODO Auto-generated method stub
		return false;
	}

}
