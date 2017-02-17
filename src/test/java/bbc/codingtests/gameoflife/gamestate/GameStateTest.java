package bbc.codingtests.gameoflife.gamestate;

import org.junit.Test;

import java.util.IllegalFormatCodePointException;

import static org.junit.Assert.*;

public class GameStateTest {

    @Test
    public void testParsing() {
        String input = ".*.\n*.*\n...";
        GameState testState = new GameStateImpl(input);
        assertTrue("Row 0, col 1 should be alive",testState.isCellAliveAt(0,1));
        assertFalse("Row 2, col 2 should not be alive",testState.isCellAliveAt(2,2));
    }

    @Test
    public void testRowColCounts() {
        String input = "...\n***\n..*";
        GameState testState = new GameStateImpl(input);
        assertEquals("The game should have 3 columns", 3, testState.getCols());
        assertEquals("The game should have 3 rows", 3, testState.getRows());
    }

    @Test
    public void testGameState(){
        String input =  "...\n***\n..*";
        GameState testState = new GameStateImpl(input);
        assertEquals("The GameState and input should be equal ", input, testState.toString());
    }

    @Test
    public void testParsingEmptyString() {
        String input = "";
        try {
            GameState testState = new GameStateImpl(input);
            fail("An empty input should result in an exception");
        } catch (IllegalArgumentException e){
            // exception expected
        }
    }

    @Test
    public void testParsingInvalidInput() {
        String input = "..\n*x\n*.";
        try {
            GameState testState = new GameStateImpl(input);
            fail("An invalid input should result in an exception");
        }
        catch(IllegalArgumentException e){
            // exception expected
        }
    }

    @Test
    public void testParsingUnevenColumns() {
        String input = "..\n*.*\n**";
        try{
            GameState testState = new GameStateImpl(input);
            fail("An input with uneven length columns should result in an exception");
        }
        catch(IllegalArgumentException e)
        {
            // exception expected
        }

    }
}
