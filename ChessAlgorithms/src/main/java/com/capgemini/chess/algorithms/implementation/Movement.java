package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public interface Movement {
	public String validate(int from, int to);
}
