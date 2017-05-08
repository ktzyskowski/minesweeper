public class NumberTile extends Tile {

    private int number;

    public NumberTile(int row, int col, int number)
    {
        super(row, col);
        this.number = number;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

}
