package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;
import java.util.ArrayList;

public class LifeImpl implements Life
{
	public GameState evolve(GameState state) {

		// if there are no live cells, there are no interactions, return the game state unchanged
		if (hasNoLiveCells(state.toString()))
			return state;

		// Get the list of cell positions that will evolve given the current state and rules of the game of life
		ArrayList<Integer[]> cellsEvolvedList = getEvolvedCellsList(state);

		// Update the game's current state by switching the cell states that have evolved
		Integer[] cell;
		int rowIdx = 0;
		int colIdx = 1;
		for (int i = 0; i != cellsEvolvedList.size(); ++i) {
			cell = cellsEvolvedList.get(i);
			state.switchCellState(cell[rowIdx], cell[colIdx]);
		}

		// Return the updated game state
		return state;
	}

	/**
	 * A function that checks the string version of the current state to determine if there are only dead cells represented
	 * @param currentStateStr
	 * @return a boolean indicating if there are no live cells
	 */
	private boolean hasNoLiveCells(String currentStateStr)
	{
		String deadCellsRegEx = "[.\n]*";
		if (currentStateStr.matches(deadCellsRegEx))
			return true;
		else
			return false;
	}

	/**
	 * A function that creates a list of cell positions that will evolve over the current iteration, given the
	 * rules of the Game of Life
	 * @param currentState	current state of the game
	 * @return	an ArrayList of cell positions that have evolved
	 */
	ArrayList<Integer[]> getEvolvedCellsList(GameState currentState)
	{
		int rowCount = currentState.getRows();
		int colCount = currentState.getCols();

		int neighbourCount = 0;
		boolean isCurrentCellAlive = false;

		// Declare an array list to store the cell positions that will evolve
		ArrayList<Integer[]> cellsEvolvedList = new ArrayList<Integer[]>();
		Integer[] cell;

		// Add the list of cells that will evolve
		for (int row = 0; row != rowCount; ++row) {
			for (int col = 0; col != colCount; ++col) {
				// count the number of neighbours of the current cell that are live
				neighbourCount = countNeighbours(currentState, row, col, rowCount, colCount);

				// if the current cell has evolved, add it to the list of evolved cells
				isCurrentCellAlive = currentState.isCellAliveAt(row, col);
				if (hasCellEvolved(isCurrentCellAlive, neighbourCount)) {
					cell = new Integer[]{row, col};
					cellsEvolvedList.add(cell);
				}
			}
		}

		return cellsEvolvedList;
	}

	/**
	 * A function that counts the number of neighbour cells that are live for a cell given its row and col
	 * This function is responsible for handling edge cases for when the cell is at the border of the board
	 * @param currentState	the current game state
	 * @param row			the row of the cell being determined
	 * @param col			the column of the cell being determined
	 * @param rowCount		the number of rows on the board
	 * @param colCount		the number of columns on the board
	 * @return
	 */
	private int countNeighbours(GameState currentState, int row, int col, int rowCount, int colCount)
	{
		int neighbourCount = 0;

		int leftBorder = 0;
		int rightBorder = colCount - 1;
		int topBorder = 0;
		int bottomBorder = rowCount - 1;

		// Neighbour States
		if (col != leftBorder)
			if (currentState.isCellAliveAt(row, col - 1))			// left
				neighbourCount++;
		if (row != topBorder && col != leftBorder)
			if (currentState.isCellAliveAt(row - 1, col - 1))	// top-left
				neighbourCount++;
		if (row != topBorder)
			if (currentState.isCellAliveAt(row - 1, col))			// top
				neighbourCount++;
		if (row != topBorder && col != rightBorder)
			if(currentState.isCellAliveAt(row - 1, col + 1))		// top-right
				neighbourCount++;
		if (col != rightBorder)
			if(currentState.isCellAliveAt(row, col + 1))				// right
				neighbourCount++;
		if (row != bottomBorder && col != rightBorder)
			if(currentState.isCellAliveAt(row+1, col+1))			// bottom-right
				neighbourCount++;
		if (row != bottomBorder)
			if(currentState.isCellAliveAt(row+1,col))				// bottom
				neighbourCount++;
		if (row != bottomBorder && col != leftBorder)
			if(currentState.isCellAliveAt(row+1,col-1))			// bottom-left
				neighbourCount++;

		return neighbourCount;
	}

	/**
	 * A function that determines if a cell has evolved given its interaction with neighbours using the rules
	 * of the game of life
	 * @param isCurrentCellAlive	state of the current cell
	 * @param neighbourCount		number of neighbours that are live
	 * @return a boolean indicating if the cell has evolved
	 */
	private boolean hasCellEvolved(boolean isCurrentCellAlive, int neighbourCount)
	{
		if (isCurrentCellAlive) {
			// if there are fewer than 2 neighbours OR  if there are more than 3 neighbours
			if (neighbourCount < 2 || neighbourCount > 3) {
				// kill the cell i.e. the state of the cell has evolved, return true
				return true;
			}
		}
		else
		{
			// if there are exactly 3 neighbours
			if (neighbourCount == 3) {
				// create a cell i.e. the state of the cell has evolved, return true
				return true;
			}
		}

		// The cell state hasn't evolved, return false
		return false;
	}
}
