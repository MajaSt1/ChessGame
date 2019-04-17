package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;
import com.capgemini.chess.algorithms.implementation.exceptions.PawnMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.RookMoveException;

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

	}

	@Test
	public void whitePawnMoveFourSpotsTest() throws PawnMoveException, InvalidMoveException {
		//
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();

		Coordinate from = new Coordinate(5, 1);
		Coordinate to = new Coordinate(5, 5);

		boolean exceptionThrown = false;
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
		Move move = pieces.getPiece();
		//
		assertTrue(exceptionThrown);
	}

	@Test
	public void whitePawnMoveOneSpotsTest() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();

		Coordinate from = new Coordinate(5, 1);
		Coordinate to = new Coordinate(5, 2);
		//
        ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
		Move move = pieces.getPiece();
		
		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());

	}

	@Test
	public void whitePawnMoveTwoSpotsFromStartingPosition() throws InvalidMoveException {
		//
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();

		Coordinate from = new Coordinate(5, 1);
		Coordinate to = new Coordinate(5, 3);
		//
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
		Move move = pieces.getPiece();

		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
	}

	@Test
	public void captureBlackPawnByWhitePawnOnLeft() throws InvalidMoveException {
		//
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 2));
		Coordinate from = new Coordinate(5, 1);
		Coordinate to = new Coordinate(4, 2);
		//
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
		Move move = pieces.getPiece();

		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
		assertEquals(move.getType(), MoveType.CAPTURE);
	}
	
	@Test
	public void moveWhitePawnFromStartingPositionTwoSpotsForwardWhenOtherPieceIsOnSpot()
			throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(5, 3));
		Coordinate from = new Coordinate(5, 1);
		Coordinate to = new Coordinate(5, 3);

		boolean exceptionThrown = false;
		try {
			ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
			PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
			Move move = pieces.getPiece();
		} catch (InvalidMoveException e) {
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}


	@Test
	public void moveBlackPawnFromStartingPositionToFrontOneSpot() throws InvalidMoveException {
		//
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();

		Coordinate from = new Coordinate(2, 6);
		Coordinate to = new Coordinate(2, 5);
		board.getMoveHistory().add(new Move());
        //
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.BLACK);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.BLACK);
		Move move = pieces.getPiece();
 		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
	}
	
	@Test
	public void moveBlackPawnFromStartingPositionTwoSpotsForwardWhenOtherPieceIsOnSpot()
			throws InvalidMoveException {
		//
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(2, 4));
		Coordinate from = new Coordinate(2, 6);
		Coordinate to = new Coordinate(2, 4);

		boolean exceptionThrown = false;
		try {
			//
			ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.BLACK);
			PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.BLACK);
			Move move = pieces.getPiece();
		} catch (InvalidMoveException e) {
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}
	
	@Test
	public void captureWhitePawnByBlackPawnOnLeft() throws InvalidMoveException {
		//
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(1, 5));
		Coordinate from = new Coordinate(2, 6);
		Coordinate to = new Coordinate(1, 5);
		board.getMoveHistory().add(new Move());
        //
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.BLACK);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.BLACK);
		Move move = pieces.getPiece();
		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
		assertEquals(move.getType(), MoveType.CAPTURE);
	}
	
	@Test
	public void shouldKnightMoveRight8() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(3, 3));
		Coordinate from = new Coordinate(3, 3);
		Coordinate to = new Coordinate(1, 4);
		board.getMoveHistory().add(new Move());
        //
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.BLACK);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.BLACK);
		Move move = pieces.getPiece();
		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
		assertEquals(move.getType(), MoveType.ATTACK);
	}
		
	@Test
	public void shouldKnightMoveTwoSpotsWithoutLShape() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(3, 3));
		Coordinate from = new Coordinate(3, 3);
		Coordinate to = new Coordinate(3, 5);
		
		boolean exceptionThrown = false;
		//
		try {
			ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.BLACK);
			PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.BLACK);
			Move move = pieces.getPiece();
		} catch (InvalidMoveException e) {
			exceptionThrown = true;
		}
		//
		assertTrue(exceptionThrown);
	}
	
	@Test
	public void shouldKnightCaptureAnotherPiece2() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(3, 3));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(5, 4));
		Coordinate from = new Coordinate(3, 3);
		Coordinate to = new Coordinate(5, 4);
		board.getMoveHistory().add(new Move());
		//
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.BLACK);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.BLACK);
		Move move = pieces.getPiece();
		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
		assertEquals(move.getType(), MoveType.CAPTURE);
	}
	
	@Test
	public void shouldBishopMoveCorrect4() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(3, 3));
		Coordinate from = new Coordinate(3, 3);
		Coordinate to = new Coordinate(6, 0);
		//
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
		Move move = pieces.getPiece();
		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
		assertEquals(move.getType(), MoveType.ATTACK);
	}
	
	@Test
	public void shouldBishopCaptureEnemyPiece1() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(3, 0));
		board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(0, 3));
		Coordinate from = new Coordinate(3, 0);
		Coordinate to = new Coordinate(0, 3);
        //
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
		Move move = pieces.getPiece();
		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
		assertEquals(move.getType(), MoveType.CAPTURE);
	}
	
	@Test
	public void shouldBishopMoveWhenHisOwnPieceIsOnDestinationSpot() throws  InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(3, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(0, 3));

		Coordinate from = new Coordinate(3, 0);
		Coordinate to = new Coordinate(0, 3);

		boolean exceptionThrown = false;
		try {
			ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
			PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
			Move move = pieces.getPiece();
		} catch (InvalidMoveException e) {
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}
	
	@Test
	public void shouldRookMoveCorrect3() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(3, 3));
		Coordinate from = new Coordinate(3, 3);
		Coordinate to = new Coordinate(3, 0);
		//
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
		Move move = pieces.getPiece();
		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
		assertEquals(move.getType(), MoveType.ATTACK);
	}
	
	@Test
	public void shouldRookMoveWhenHisOwnPieceIsOnDestinationSpot() throws RookMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(3, 3));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(0, 3));

		Coordinate from = new Coordinate(3, 3);
		Coordinate to = new Coordinate(0, 3);

		boolean exceptionThrown = false;
		try {
			ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
			PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
			Move move = pieces.getPiece();
		} catch (InvalidMoveException e) {
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}
	
	@Test
	public void shouldRookCaptureEnemyPiece2() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(2, 0));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(2, 7));

		Coordinate from = new Coordinate(2, 0);
		Coordinate to = new Coordinate(2, 7);
		//
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
		Move move = pieces.getPiece();
		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
		assertEquals(move.getType(), MoveType.CAPTURE);
	}

	@Test
	public void shouldQueenMoveCorrect7() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(0, 0));
		Coordinate from = new Coordinate(0, 0);
		Coordinate to = new Coordinate(7, 7);
		//
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
		Move move = pieces.getPiece();
		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
		assertEquals(move.getType(), MoveType.ATTACK);
	}
	
	@Test
	public void shouldQueenMoveWhenEnemyIsOnHisWay() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(1, 3));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(3, 3));

		Coordinate from = new Coordinate(1, 3);
		Coordinate to = new Coordinate(7, 3);

		boolean exceptionThrown = false;
		try {
			ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
			PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
			Move move = pieces.getPiece();
		} catch (InvalidMoveException e) {
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}
	@Test
	public void shouldQueenCaptureEnemyPiece1() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 3));
		board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(4, 2));

		Coordinate from = new Coordinate(3, 3);
		Coordinate to = new Coordinate(4, 2);
		//
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
		Move move = pieces.getPiece();
		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
		assertEquals(move.getType(), MoveType.CAPTURE);
	}
	
	@Test
	public void shouldKingMoveCorrect1() throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(3, 3));
		Coordinate from = new Coordinate(3, 3);
		Coordinate to = new Coordinate(2, 4);
		//
		ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
		PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
		Move move = pieces.getPiece();
		//
		assertEquals(move.getFrom().getX(), from.getX());
		assertEquals(move.getFrom().getY(), from.getY());
		assertEquals(move.getTo().getX(), to.getX());
		assertEquals(move.getTo().getY(), to.getY());
		assertEquals(move.getType(), MoveType.ATTACK);
	}
	
	@Test
	public void shouldReturnFalseWhenOnlyOneWhitePawnIsAtTheEndOfBoard()
			throws InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(3, 7));

		AbstractPieceMove abstractPieceMove = null;

		assertFalse(abstractPieceMove.isAnyMoveValid(Color.WHITE, board));
	}
	
	@Test
	public void shouldReturnKingExceptionWhenAfterMovementWhiteKingIsInCheck()
			throws KingInCheckException, InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(0, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(0, 1));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 7));
		Coordinate from = new Coordinate(0, 1);
		Coordinate to = new Coordinate(2, 2);

		boolean exceptionThrown = false;
		try {
			ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
			PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
			Move move = pieces.getPiece();
		} catch (KingInCheckException e) {
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}
	
	@Test
	public void shouldReturnKingExceptionWhenAfterMovementBlackKingIsInCheck()
			throws KingInCheckException, InvalidMoveException {
		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceAt(null, new Coordinate(i, j));
			}
		}
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(7, 7));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(7, 6));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));
		Coordinate from = new Coordinate(7, 6);
		Coordinate to = new Coordinate(5, 5);
		board.getMoveHistory().add(new Move());
		boolean exceptionThrown = false;
		try {
			ConditionMovement conditionMovement = new ConditionMovement(from, to, board, Color.WHITE);
			PiecesMoveFactory pieces = new PiecesMoveFactory(conditionMovement, from, to, board, Color.WHITE);
			Move move = pieces.getPiece();
		} catch (KingInCheckException e) {
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}

 
}
