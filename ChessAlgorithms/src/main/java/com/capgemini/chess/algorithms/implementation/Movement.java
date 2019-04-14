package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public interface Movement {
	boolean validate(Coordinate from, Coordinate to) throws InvalidMoveException;
	boolean isMoveBlocked(Coordinate from, Coordinate to) throws InvalidMoveException;
}
