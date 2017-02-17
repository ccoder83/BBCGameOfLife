package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;

public interface Life
{
	/**
	 * Apply the rules of the Game of Life to the given state and return the resulting state
     */
	/**
	 *	This function applies the rules of the Game of Life to the given state over one iteration
	 * 	and returns the updated state of the game
	 * @param state the current state of the game
	 * @return an updated state of the game
	 */
	GameState evolve(GameState state);
}
