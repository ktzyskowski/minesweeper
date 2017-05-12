import java.util.ArrayList;

public class Minesweeper {

    private Tile[][] board;
    private int bombs;
    private ArrayList<Coord> reveals;
    private ArrayList<Coord> visted;

    /**
     * Constructor for Minesweeper game.
     * @param rows  Amount of rows
     * @param cols  Amount of columns
     * @param bombs Number of bombs to place
     */
    public Minesweeper(int rows, int cols, int bombs)
    {
        this.board = new Tile[rows][cols];
        this.bombs = bombs;
        this.generateBoard();
        this.reveals = new ArrayList<>();
        this.visted = new ArrayList<>();
    }

    /**
     * Generates the game's board
     */
    public void generateBoard()
    {
        // Generate blank tiles
        for (int i = 0; i < board.length; ++i){
            for (int j = 0; j < board[i].length; ++j){
                board[i][j] = new Tile();
            }
        }

        // Generate Mine tiles
        while (bombs > 0){
            int r = (int)(Math.random()*board.length);
            int c = (int)(Math.random()*board[r].length);
            if (!(board[r][c] instanceof Mine)){
                board[r][c] = new Mine();
                --bombs;
            }
        }

        // Generate Number tiles
        for (int i = 0; i < board.length; ++i){
            for (int j = 0; j < board[i].length; ++j){
                if (getNumber(i,j) != 0){
                    board[i][j] = new NumberTile(getNumber(i,j));
                }
            }
        }
    }

    /**
     * Method to get a list of tiles to reveal on click on origin.
     * @param row
     * @param col
     * @return
     */
    public ArrayList<Coord> getReveals(int row, int col)
    {
        reveals = new ArrayList<>();
        visted = new ArrayList<>();
        if (board[row][col] instanceof Mine) {
            reveals.add(new Coord(row, col));
        } else {
            reveals.add(new Coord(row, col));
            floodReveals(row, col);
        }

        return reveals;
    }

    /**
     * Method to recursively compute what tiles to reveal (flood-like algorithm).
     * @param row   The specified row of tile
     * @param col   The specified column of tile
     */
    public void floodReveals(int row, int col)
    {
        for (Coord c : getAdjacent(row, col)){
            if (!visited(c) && !(board[c.row][c.col] instanceof Mine)){
                reveals.add(c);
                floodReveals(c.row, c.col);
            }
        }
    }


    /**
     * Method to deduce if a tile has been visited or not.
     * @param c The coordinate to determine if visited
     * @return  True if it has been visited, false otherwise
     */
    public boolean visited(Coord c)
    {
        for (Coord x : reveals){
            if (c.equals(x))
                return true;
        }
        return false;
    }

    /**
     * Method to deduce the number of surrounding tiles.
     * @param row   The specified row of the tile
     * @param col   The specified column of the tile
     * @return      The number of mines surrounding the tile; 0 for blank or if Mine
     */
    public int getNumber(int row, int col)
    {
        int numberOfMines = 0;

        if (board[row][col] instanceof Mine)
            return 0;

        for (Coord c : getAdjacent(row, col)){
            if (board[c.row][c.col] instanceof Mine)
                ++numberOfMines;
        }

        return numberOfMines;
    }

    /**
     * Method to get the coordinates of adjacent tiles.
     * @param row   The specified row of the tile
     * @param col   The specified column of the tile
     * @return      ArrayList containing Coord objects of locations of valid adjacent tiles
     */
    public ArrayList<Coord> getAdjacent(int row, int col)
    {
        ArrayList<Coord> toReturn = new ArrayList<>();
        for (int i = row - 1; i < row + 2; ++i){
            for (int j = col - 1; j < col + 2; ++j){
                if (isInBounds(i, j)){
                    toReturn.add(new Coord(i, j));
                }
            }
        }
        return toReturn;
    }

    /**
     * Method to deduce if a given coordinate is in bounds.
     * @param row   The specified row of the tile
     * @param col   The specified column of the tile
     * @return      True if Tile is in bounds of board, false otherwise
     */
    public boolean isInBounds(int row, int col)
    {
        if (row > -1 && row < board.length && col > -1 && col < board[row].length)
            return true;
        return false;
    }

    @Override
    public String toString()
    {
        String out = "";
        for (Tile[] sub : board){
            for (Tile t : sub){
                out += t;
            }
            out += "\n";
        }

        return out;
    }

}
