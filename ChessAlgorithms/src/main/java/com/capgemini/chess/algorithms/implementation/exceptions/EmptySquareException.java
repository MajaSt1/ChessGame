package com.capgemini.chess.algorithms.implementation.exceptions;

public class EmptySquareException extends Exception{
	
	 private static final long serialVersionUID = 1L;
	 
	 public EmptySquareException(){
		 super("The square you're choose is empty");
	 }
	 
	 public EmptySquareException(String message){
		 super("The square you're choose is empty" + message);
	 }

}
