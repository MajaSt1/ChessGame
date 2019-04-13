package com.capgemini.chess.algorithms.implementation;

import org.hamcrest.core.Is;

import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;

import javafx.scene.chart.ValueAxis;

public class AbstractPieceMove{
	
	private Movement movement;
	
	public AbstractPieceMove(Movement movement){
		 this.movement= movement;
	}
	 public void isMovevalid (int fromX,int fromY,int toX,int toY) {
			if(fromX < 0 || fromY < 0 || fromX > 8 || fromY > 8 || toX < 0 || toY < 0 || toX > 8 || toY > 8){
				throw new InvalidMoveException();
		 } 
			if (fromX == toX || fromY == toY){
		
			}	
			if (Math.abs(toX - fromX) == Math.abs(fromY - toY)){
			return true;
			}
			}
	    }
//from to, na planszy? 
	// from=to - miejsce poczatkowe
	// czy ti jest nasz pion
	// odslona krola- king in check (po wykonanym ruchu) 
	// czy from ma nasza figure 
	// opisywac exceptiony = konkretnie 
	/*
	 * */
	 }
	    
	  //  InvalidMoveException, KingInCheckException
	    
	    

}
