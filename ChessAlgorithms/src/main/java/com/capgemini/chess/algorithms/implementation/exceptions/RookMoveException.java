package com.capgemini.chess.algorithms.implementation.exceptions;

public class RookMoveException extends Exception {
	private static final long serialVersionUID = 1L;
	
	 public RookMoveException() {
		super("Cant move like that");
	}
}
