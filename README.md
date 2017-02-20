## BBCGameOfLife ##

The Game of Life is a cellular automaton model, where evolution is determined by its initial state, requiring no further input.

This project takes an initial state as input, and evolves it by one iteration, given by the rules of the Game of Life.

The Game of Life's world is represented as a two-dimensional grid of cells, each of which has one of two states, dead represented by '.', and alive represented by '*'. The world's initial state input is represented by a string, with a combination of dead and alive cells, with the newline ('\n') symbol separating the rows. On an iteration, every cell interacts with its eight neighbours, which are the cells located horizontally, vertically and diagonally adjacent. The following interactions occur, and the cells transition as follows:

    When a live cell has fewer than 2 neighbours or greater than 3 neighbours, then the cell dies.
    When a live cell has 2 or 3 neighbours, then the cell stays alive
    When a dead cell has exactly 3 neighbours, it becomes alive

## Assumptions ##

1. The input data to the GameState class may not be clean
2. GameState is responsible for determining the input data for the initial state of the game is error-free
3. If the input is invalid, GameState throws an exception

## Task ##

Your task is to implement the rules of Conway's Game of Life as explained in the document statement. You can use the code skeleton provided here as a starting point. In that case, implement the `evolve` method in `LifeImpl.java`. There are also some helper methods in `GameStateImpl.java` Feel free to make changes to the code that help you capture all the rules of the game. You can add more tests in `GameStateTest.java` and `LifeTest.java` to verify the correctness of your code.

## Running the tests ##

### Command line ###
You can run this project using your favorite text editor and the command line. You just need to have java 1.7 or later and [maven](https://maven.apache.org/download.cgi) installed.
Then, from your command line, run `mvn test` in this directory in order to run all the tests.

### Eclipse ###

To run this project in Eclipse:
1. Install [m2e](http://www.eclipse.org/m2e/) in your Eclipse. Restart Eclipse as required
2. Go to File -> Import, select Maven -> Existing Maven Project, then select this folder
3. You should now be able to run any of the files in the `test` folder as required

### Intellij ###
Go to File -> Open, then select the `pom.xml` file in this folder. You should be able to run any of the test files as required.
