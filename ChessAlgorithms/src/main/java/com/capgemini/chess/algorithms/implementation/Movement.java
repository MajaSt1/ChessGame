package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.BishopMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KnightMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.NoKingAtTheBoardException;
import com.capgemini.chess.algorithms.implementation.exceptions.QueenMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.RookMoveException;

public interface Movement {
	 /**
     * Checks to see if the king of the given color is in check. This will be called twice after every move
     * @param kingColor
     * @return boolean value corresponding to the king being in check
	 * @throws NoKingAtTheBoardException 
     */
	boolean validatePieceMove(Coordinate from, Coordinate to, Color color, Board board) throws InvalidMoveException, KnightMoveException, RookMoveException, QueenMoveException, KingMoveException; //generate possible movement

	 /**
     * Checks to see if the king of the given color is in check. This will be called twice after every move
     * @param kingColor
     * @return boolean value corresponding to the king being in check
	 * @throws NoKingAtTheBoardException 
     */
	boolean isMoveBlocked(Coordinate from, Coordinate to) throws InvalidMoveException, BishopMoveException, RookMoveException;
}
