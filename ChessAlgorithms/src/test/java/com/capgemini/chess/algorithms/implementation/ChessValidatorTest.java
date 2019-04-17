package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class ChessValidatorTest {
	
	 @Test
	public void validateMove() {
      //// given
		List<Move> moves = new ArrayList<>();
		Move move = new Move();
		move.setFrom(new Coordinate(0, 0));
		move.setTo(new Coordinate(0, 6));
		move.setType(MoveType.CAPTURE);
		moves.add(move);
		
		// when
		BoardManager boardManager = new BoardManager(moves);
		
		// then
		assertNull(boardManager.getBoard().getPieceAt(new Coordinate(0, 0)));
		assertNotNull(boardManager.getBoard().getPieceAt(new Coordinate(0, 6)));
		//assertEquals(31, calculateNumberOfPieces(boardManager.getBoard()));
		
	} 
	 @Test
		public void moveWhitePawnFromStartingPositionToFrontTwoSpots() throws InvalidMoveException {
		    //
			BoardManager boardManager = new BoardManager();
			Board board = boardManager.getBoard();
           //
			Coordinate from = new Coordinate(5, 1);
			Coordinate to = new Coordinate(5, 3);
			
			ConditionMovement conditionMovement= new ConditionMovement(from, to, board, Color.WHITE);
			PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
			Move move = pieces.getPiece();
			
	         //
			assertEquals(move.getFrom().getX(), from.getX());
			assertEquals(move.getFrom().getY(), from.getY());
			assertEquals(move.getTo().getX(), to.getX());
			assertEquals(move.getTo().getY(), to.getY());
		}
}
