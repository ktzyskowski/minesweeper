public class MinesweeperGame
{
  private Tile[][] board;

  /** constructor for a MinesweeperGame class.
    * @param rows total number of rows in new board.
    * @param cols total number of columns in new board.
    * @param bombs total number of bombs in new board.
   */
  public MinesweeperGame(int rows, int cols, int bombs)
  {
    board = new Tile[rows][cols];

    for (int r = 0; r < rows; r++)
    {
      for (int c = 0; c < cols; c++)
      {
        board[r][c] = new Tile();
      }
    }

    // ... disperse bombs randomly
    while (bombs > 0)
    {
      int randRow = (int)(Math.random() * rows);
      int randCol = (int)(Math.random() * cols);
      if (! (board[randRow][randCol] instanceof Bomb))
      {
        board[randRow][randCol] = new Bomb();
        bombs--;
      }
    }

  }

  /** method to return a number from 0 to 8 indicating the number of bombs
    * around a specific tile.
    * @return returns number in correspondance to number of bombs
    * around the tile.
    * @param row row of the tile to get the number for.
    * @param col column of the tile to get the number for.
   */
  public int getNumber(int row, int col)
  {
    int number = 0;
    for (int r = row - 1; r <= row + 1; r++)
    {
      for (int c = col - 1; c <= col + 1; c++)
      {
        if (r >= 0 && c >= 0 && r < board.length && c < board[row].length && (r != row && c != col) && board[r][c] instanceof Bomb)
        {
            number++;
        }
      }
    }
    return number;

  }

  /** getter method for the board.
   */
  public Tile[][] getBoard()
  {
    return board;

  }

  /** updator method for the board. Sets the position at row, col to a new tile and
    * returns the old tile.
    * @param row row of the tile being updated.
    * @param col column of the tile being updated.
    * @param newTile new tile to replace old tile.
    * @return returns the tile that is being replaced by newTile.
   */
  public Tile updateTile(int row, int col, Tile newTile)
  {
    Tile oldTile = board[row][col];
    board[row][col] = newTile;
    return oldTile;

}
