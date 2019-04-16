package com.capgemini.chess.algorithms.implementation.exceptions;

public class OpponentTurnException extends Exception{
	
	 private static final long serialVersionUID = 1L;
	 
	 public OpponentTurnException(){
		 super("Opponent turn");
	 }
	 
	 public OpponentTurnException(String message){
		 super("Opponent turn" + message);
	 }
}
