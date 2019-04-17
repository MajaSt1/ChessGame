package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

/**
 * Test suite containing all tests
 *
 * @author Michal Bejm
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ BoardManagerTest.class }) //nie jest potrzebne wlasciwie
public class ChessTestSuite {

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
		assertEquals(31, calculateNumberOfPieces(boardManager.getBoard()));
		
	} 
	 
	@Test
	public void kingChecked(){
		// given 
		Board board = new Board();
		board.setPieceAt(Piece., coordinate);
		//when 
		//then
	}

}
