public class Coord {

    public int row, col;

    public Coord(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public boolean equals(Coord other)
    {
        return (this.row == other.row && this.col == other.col);
    }

}
