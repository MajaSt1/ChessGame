package com.capgemini.chess.algorithms.implementation.exceptions;

public class KnightMoveException extends Exception{
	
 private static final long serialVersionUID = 1L;
	 
	 public KnightMoveException(){
		 super("Can't move like that");
	 }

}
