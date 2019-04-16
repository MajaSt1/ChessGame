package com.capgemini.chess.algorithms.implementation.exceptions;

public class QueenMoveException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public QueenMoveException(){
		super("Cant move like that");
	}
}
