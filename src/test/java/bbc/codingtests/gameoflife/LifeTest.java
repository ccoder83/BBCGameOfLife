package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class LifeTest {

	@Test
	public void testEmptyGrid() {
		String emptyStateInput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState emptyState = new GameStateImpl(emptyStateInput);
		assertEquals("An empty grid should stay the same", emptyStateInput, testLife.evolve(emptyState).toString());
	}

	@Test
	public void testUnderPopulation(){
		String underPopulationInput = "...\n**.\n...";
		String expectedStateOutput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState underPopulationState = new GameStateImpl(underPopulationInput);

		assertEquals("An underpopulated grid with two live neighbouring cells should result in an empty grid",
				expectedStateOutput, testLife.evolve(underPopulationState).toString());
	}

	@Test
	public void testOverCrowding(){
		String overCrowdingInput = "*...\n***.\n..*.";
		String expectedStateOutput = "*...\n*.*.\n..*.";

		Life testLife = new LifeImpl();
		GameState overCrowdingState = new GameStateImpl(overCrowdingInput);

		assertEquals("An overcrowded grid with a cell of 4 neighbours should result in it dying",
				expectedStateOutput, testLife.evolve(overCrowdingState).toString());
	}

	@Test
	public void testSurvival(){
		String survivalInput = "**.\n**.\n...";

		Life testLife = new LifeImpl();
		GameState survivalState = new GameStateImpl(survivalInput);

		assertEquals("A grid of live cells with 2 or 3 neighbours and dead cells with no 3 neighbours should stay the same",
				survivalInput, testLife.evolve(survivalState).toString());
	}

	@Test
	public void testLifeCreation(){
		String lifeCreationInput = "...\n.**\n.*.";
		String expectedStateOutput = "...\n.**\n.**";

		Life testLife = new LifeImpl();
		GameState lifeCreationState = new GameStateImpl(lifeCreationInput);

		assertEquals("A grid that contains a dead cell with 3 neighbours exactly, should become alive",
				expectedStateOutput, testLife.evolve(lifeCreationState).toString());
	}

	@Test
	public void testSeededGrid() {
		String stateInput = "...\n***\n...";

		Life testLife = new LifeImpl();
		GameState gameState = new GameStateImpl(stateInput);

		String expectedStateOutput = ".*.\n.*.\n.*.";
		gameState = testLife.evolve(gameState);
		assertEquals("The game has not evolved to the expected state", expectedStateOutput, gameState.toString());

		gameState = testLife.evolve(gameState);
		assertEquals("The game should evolve back to the initial state", stateInput, gameState.toString() );
	}


}
