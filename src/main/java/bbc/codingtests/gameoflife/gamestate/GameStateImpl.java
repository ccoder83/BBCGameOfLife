package bbc.codingtests.gameoflife.gamestate;

public class GameStateImpl implements GameState {

    private char[][] m_BoardArray;
    private int m_RowCount;
    private int m_ColCount;

    /**
     * A function to convert the game state into a String such that live cells are represented as a '*' and dead
     * cells are represented by a '.', and newline ('\n') is used to separate rows
     * @return the game state as a String
     */
    @Override
    public String toString() {

        // Convert the board array to a string
        String boardString = "";

        // Append rowCount-1 rows to the string with new line symbol
        for (int r = 0; r != m_RowCount-1; ++r) {
            for (int c = 0; c != m_ColCount; ++c) {
                boardString += m_BoardArray[r][c];
            }
            boardString += '\n';
        }

        // Append last row without new line symbol
        for (int c = 0; c != m_ColCount; ++c)
            boardString += m_BoardArray[m_RowCount-1][c];

        return boardString;
    }

    /**
     * Constructor that initialises a game state by parsing an input string where live cells are represented as '*' and
     * dead cells as '.'. Rows are separated by newline ('\n').
     * The constructor is responsible for error checking and ensuring the input string is valid, and throws exceptions otherwise.
     * @param input input game state
     */
    public GameStateImpl(String input) {

        if (input.isEmpty()){
            throw new IllegalArgumentException("Input is empty. Please provide a non-empty input.");
        }

        // Ensure the input is valid
        String validInputRegEx = "[.*\n]*";
        if (!input.matches(validInputRegEx)){
            throw new IllegalArgumentException("Input contains invalid characters. Please provide valid input containing only '.', '*' and '\\n'");
        }

        // Split string by the newline symbol into a string array
        String newLineDelim = "\n";
        String[] rows = input.split(newLineDelim);

        // Number of rows given by number of splits
        m_RowCount = rows.length;

        // Assign length of first split to number of columns
        m_ColCount = rows[0].length();

        // Ensure all columns are of equal length
        for (int i = 1; i != m_RowCount; ++i) {
            if (rows[i].length() != m_ColCount)
                throw new IllegalArgumentException("Input contains columns of unequal length. Please provide an input with equal length columns.");
        }

        // Initialise our Game of Life board
        m_BoardArray = new char[m_RowCount][m_ColCount];

        // Read the input characters into the board array
        for (int r = 0; r != m_RowCount; ++r) {
            for (int c = 0; c != m_ColCount; ++c) {
                // Read character into the Board Array[r][c]
                m_BoardArray[r][c] = rows[r].charAt(c);
            }
        }
    }

    public void switchCellState(int row, int col)
    {
        if (m_BoardArray[row][col] == '*')
            m_BoardArray[row][col] = '.';
        else
            m_BoardArray[row][col] = '*';
    }

    public boolean isCellAliveAt(int row, int col) {

        // if row or col is outside the grid, return false
        if (row < 0 || row >= m_RowCount || col < 0 || col >= m_ColCount)
            return false;

        // if the cell is alive return true
        if (m_BoardArray[row][col] == '*')
            return true;
        else
            return false;
    }

    @Override
    public int getRows() {
        return m_RowCount;
    }

    @Override
    public int getCols() {
        return m_ColCount;
    }
}
