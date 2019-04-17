package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class PiecesMoveFactory {

	private ConditionMovement conditionMovement;
	PieceType type = PieceType.BISHOP;
	Color color;
	private Coordinate from;
	private Board board;
	private Coordinate to;

	public PiecesMoveFactory(ConditionMovement conditionMovement,Coordinate from, Coordinate to, Board board, Color color) {
		this.conditionMovement = conditionMovement;
		this.color = color;
		this.from = from;
		this.to=to;
		this.board=board;
	}

	public Move getPiece() throws InvalidMoveException {
		switch (type = conditionMovement.getBoard().getPieceAt(conditionMovement.getFrom()).getType()) {

		case BISHOP: {
			return conditionMovement.playerCanMoveAtTurn(new Bishop(color), from, from, board);
		}
		case PAWN: {
			return conditionMovement.playerCanMoveAtTurn(new Pawn(color), from, from, board);
		}
		case KING: {
			return conditionMovement.playerCanMoveAtTurn(new King(color), from, from, board);
		}
		case QUEEN: {
			return conditionMovement.playerCanMoveAtTurn(new Queen(color), from, from, board);
		}
		case KNIGHT: {
			return conditionMovement.playerCanMoveAtTurn(new Knight(color), from, from, board);
		}
		case ROOK: {
			return conditionMovement.playerCanMoveAtTurn(new Rook(color), from, from, board);
		}
		}
		throw new InvalidMoveException();

	}

	public Coordinate getTo() {
		return to;
	}

	public void setTo(Coordinate to) {
		this.to = to;
	}

}
