package com.capgemini.chess.algorithms.data.enums;

/**
 * Board state
 * 
 * @author Michal Bejm
 *
 */
public enum BoardState {
	REGULAR, // regular move
	CHECK, 
	CHECK_MATE,
	STALE_MATE; //no legal move
}
