package minesweeper;

import java.util.ArrayList;

/**
 * Class to implement a minesweeper.Minesweeper game
 */
public class Minesweeper {

    private Tile[][] board;
    private int bombs;
    private ArrayList<Tile> reveals;
    private ArrayList<Tile> visted;
    private BiMap<Tile, Coord> quickLocations;

    /**
     * Constructor for minesweeper.Minesweeper game.
     * @param rows  Amount of rows
     * @param cols  Amount of columns
     * @param bombs Number of bombs to place
     */
    public Minesweeper(int rows, int cols, int bombs)
    {
        this.board = new Tile[rows][cols];
        quickLocations = new BiMap<Tile, Coord>();
        this.reveals = new ArrayList<>();
        this.visted = new ArrayList<>();
        this.bombs = bombs;
        this.generateBoard();
    }

    /**
     * Returns the internal board
     * @return retuns board used for calculations
     */
    public Tile[][] getBoard() {
        return board;
    }

    /**
     * Generates the game's board
     */
    public void generateBoard()
    {
        // Generate blank tiles
        for (int i = 0; i < board.length; ++i){
            for (int j = 0; j < board[i].length; ++j){
                Tile t = new Tile();
                Coord coord = new Coord(i, j);
                board[i][j] = t;
                quickLocations.put(t, coord);
            }
        }

        // Generate minesweeper.Mine tiles
        while (bombs > 0){
            int r = (int)(Math.random()*board.length);
            int c = (int)(Math.random()*board[r].length);
            if (!(board[r][c] instanceof Mine)){
                Mine m = new Mine();
                Coord coord = new Coord(r, c);
                board[r][c] = m;
                quickLocations.put(m, coord);
                --bombs;
            }
        }

        // Generate Number tiles
        for (int i = 0; i < board.length; ++i){
            for (int j = 0; j < board[i].length; ++j){
                if (getNumber(i,j) != 0){
                    NumberTile n = new NumberTile(getNumber(i,j));
                    Coord coord = new Coord(i, j);
                    board[i][j] = n;
                    quickLocations.put(n, coord);
                }
            }
        }
    }

    /**
     * Method to get a list of Tiles to reveal on click on origin.
     * @param t Specified root minesweeper.Tile
     * @return Arraylist containing Tiles to reveal
     */
    public ArrayList<Tile> getReveals(Tile t)
    {
        int row = (int)quickLocations.get(t).x;
        int col = (int)quickLocations.get(t).y;
        reveals = new ArrayList<>();
        visted = new ArrayList<>();
        if (board[row][col] instanceof Mine) {
            reveals.add(board[row][col]);
        } else {
            reveals.add(board[row][col]);
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
        if (board[row][col] instanceof NumberTile){
            reveals.add(board[row][col]);
            return;
        }
        for (Tile t : getAdjacent(row, col)){
            if (!visited(t) && !(t instanceof Mine)){
                    reveals.add(t);
                    floodReveals((int)quickLocations.get(t).x, (int)quickLocations.get(t).y);

            }
        }
    }


    /**
     * Method to deduce if a tile has been visited or not.
     * @param c The coordinate to determine if visited
     * @return  True if it has been visited, false otherwise
     */
    public boolean visited(Tile c)
    {
        return reveals.contains(c);
    }

    /**
     * Method to deduce the number of surrounding tiles.
     * @param row   The specified row of the tile
     * @param col   The specified column of the tile
     * @return      The number of mines surrounding the tile; 0 for blank or if minesweeper.Mine
     */
    public int getNumber(int row, int col)
    {
        int numberOfMines = 0;

        if (board[row][col] instanceof Mine)
            return 0;

        for (Tile t : getAdjacent(row, col)){
            if (t instanceof Mine)
                ++numberOfMines;
        }

        return numberOfMines;
    }


    /**
     * Method to get the coordinates of adjacent tiles.
     * @param row   The specified row of the tile
     * @param col   The specified column of the tile
     * @return      ArrayList containing minesweeper.Tile objects of locations of valid adjacent tiles
     */
    public ArrayList<Tile> getAdjacent(int row, int col)
    {
        ArrayList<Tile> toReturn = new ArrayList<>();
        for (int i = row - 1; i < row + 2; ++i){
            for (int j = col - 1; j < col + 2; ++j){
                if (isInBounds(i, j)){
                    toReturn.add(board[i][j]);
                }
            }
        }
        return toReturn;
    }

    /**
     * Method to deduce if a given coordinate is in bounds.
     * @param row   The specified row of the tile
     * @param col   The specified column of the tile
     * @return      True if minesweeper.Tile is in bounds of board, false otherwise
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
