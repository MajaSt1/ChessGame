package com.capgemini.chess.algorithms.implementation.exceptions;

public class KingMoveException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public KingMoveException(){
		super("Cant move like that");
	}
}
