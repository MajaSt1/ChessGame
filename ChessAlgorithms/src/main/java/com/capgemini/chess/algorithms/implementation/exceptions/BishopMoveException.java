package com.capgemini.chess.algorithms.implementation.exceptions;

public class BishopMoveException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BishopMoveException(){
		super("Can't move bishop like that");
	}
	
	public BishopMoveException( String message){
		super("Can't move bishop like that" + message);
	}
}
