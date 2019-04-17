package com.capgemini.chess.algorithms.implementation.exceptions;

public class PawnMoveException extends Exception {

	private static final long serialVersionUID = 1L;

	public PawnMoveException() {
		super("Another piece at the path");
	}
}
