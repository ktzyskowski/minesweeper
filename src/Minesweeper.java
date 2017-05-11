public class Minesweeper {

    private Tile[][] board;

    public Minesweeper(Tile[][] board)
    {
        this.board = board;
    }


    /**
     * @param row   The specified row of the tile
     * @param col   The specified column of the tile
     * @return      The number of mines surrounding the tile
     */
    public int getNumber(int row, int col)
    {
        int numberOfMines = 0;

        for (int i = row - 1; i < row + 2; ++i){
            for (int j = col - 1; j < col + 2; ++j){
                if (isInBounds(i, j)){
                    if (board[i][j] instanceof Mine)
                        ++numberOfMines;
                }
            }
        }

        return numberOfMines;
    }


    /**
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

}
