package com.capgemini.chess.algorithms.data.enums;

/**
 * Types of moves
 * 
 * @author Michal Bejm
 *
 */
public enum MoveType {
	ATTACK, //threaten the capture of a piece or pawn
	CAPTURE,// move by a pawn or piece that removes from the board the opponent’s pawn or piece
	CASTLING,  //A move in which the king and a rook are moved at the same time
	EN_PASSANT; 
	/**
	 * The rule that allows a pawn that has just advanced two squares 
	 * to be captured by an enemy pawn that is on the same rank and adjacent file.
	 * The pawn can be taken as if it had advanced only one square.
	 * Capturing en passant is possible only on the next move.*/
}
