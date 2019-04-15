package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class ConditionMovement extends AbstractPieceMove{

	private Coordinate from;
	private Coordinate to;
	private Move move = new Move();
	
	public ConditionMovement(Coordinate from, Coordinate to) {
		this.from= from;
		this.to = to;
	}

	public Move checkingValidationWithCondition(Movement movement) throws InvalidMoveException {
		if (isNotEmpty(from))
			if (isPiecePositionOnBoard(to, from))
				if (movement.isMoveBlocked(from, to))
					if (isKingInCheck())
						if (kindOfMoveType(from, to) != null)
							if (movement.validate(from, to)) {

								move.setFrom(from);
								move.setTo(to);
								move.setType(kindOfMoveType(to, from));
								move.setMovedPiece(movement.getMovedPiece());
								return move;
							}
		return null;
	}

	public Coordinate getFrom() {
		return from;
	}

}
