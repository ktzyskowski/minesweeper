import java.util.HashMap;

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
        board[r][c] = new Tile(r,c);
      }
    }

    // ... disperse bombs randomly
    while (bombs > 0)
    {
      int randRow = (int)(Math.random() * rows);
      int randCol = (int)(Math.random() * cols);
      if (! (board[randRow][randCol] instanceof Mine))
      {
        board[randRow][randCol] = new Mine(randRow,randCol);
        bombs--;
      }
    }

    // ... add numbered tiles
    for (int r = 0; r < board.length; r++)
    {
      for (int c = 0; c < board[r].length; c++)
      {
        if (getNumber(r, c) > 0)
        {
          board[r][c] = new NumberTile(getNumber(r, c), r, c);
        }
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

    for (int i = row - 1; i < row + 2; ++i){
      for (int j = col - 1; j < col + 2; ++j){
        if (i > 0 && i < board.length){
          if (j > 0 && j < board[0].length){
            if (i != row && j != col){
              if (board[i][j] instanceof Mine)
                ++number;
            }
          }
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

  /** method that returns a hashmap of tiles to be revealed on click
   */
  public HashMap<int[], Tile> getReveals(int startPosX, int startPosY)
  {
      // create new HashMap tileReturn with key of Tile & value of its position in 2D array
      HashMap<int[], Tile> tileReturn = new HashMap<>();

      // starting at startPosX & startPosY, go through surrounding areas until you are 1 tile away from bomb :    | you | bomb |
      // calculate adjacent bombs, tileReturn.put(new NumberTile(numAdj), row, column)
      int [] pos = {startPosX, startPosY};
      Tile startTile = board[startPosX][startPosY];
      tileReturn.put(pos, startTile);
      getRevealsHelper(pos[0], pos[1], tileReturn);

      // once nothing left to reveal, return tileReturn

      return tileReturn;
  }

  /** helper method for getReveals() method.
   */
  private boolean getRevealsHelper(int startPosX, int startPosY, HashMap<int[], Tile> hashMap)
  {
    int [] pos = {startPosX, startPosY};
    if (! (hashMap.containsKey(pos))) {
      // ... check if NumberTile
      if (board[pos[0]][pos[1]] instanceof NumberTile) {
        hashMap.put(pos, board[startPosX][startPosY]);
        return false;
      }
      // ... check if mine
      else if (board[pos[0]][pos[1]] instanceof Mine)
      {
        return false;
      }
      // ... must be a blank tile, continue recursion
      else {
        // ... check for bounds
        if (startPosX > 0 && startPosX < board.length && startPosY > 0 && startPosY <board[startPosX].length)
        {
          hashMap.put(pos, new NumberTile(startPosX, startPosY, getNumber(startPosX, startPosY)));
          for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
              return getRevealsHelper(startPosX + i, startPosY + j, hashMap);
            }
          }
        }
      else
        {
          return false;
        }
      }
    }
    return false;
  }

}
