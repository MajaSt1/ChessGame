package com.capgemini.chess.algorithms.implementation.exceptions;

public class NoKingAtTheBoardException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public NoKingAtTheBoardException(){
		super("No king detected! ");
	}
}
