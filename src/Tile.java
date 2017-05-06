public class Tile
{
  private String type;

  /** specific constructor of Tile class. To be called by subclasses
    * in super() calls.
   */
  public Tile(String type)
  {
    this.type = type;

  }

  /** generic constructor to be used in construction of Tile objects.
   */
  public Tile()
  {
    this("Tile");

  }

  /**
    * @return returns type of Tile.
   */
  public String getTileType()
  {
    return type;

  }

}
