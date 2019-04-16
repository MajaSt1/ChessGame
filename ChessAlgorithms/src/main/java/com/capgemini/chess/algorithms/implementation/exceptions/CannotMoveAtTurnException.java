package com.capgemini.chess.algorithms.implementation.exceptions;

public class CannotMoveAtTurnException extends Exception{
	
	private static final long serialVersionUID = 1L;
	 
	 public CannotMoveAtTurnException() {
		 super("Any move valid");
	 }
	 
	 public CannotMoveAtTurnException(String message) {
		 super("Any move valid" + message);
	 }
}
