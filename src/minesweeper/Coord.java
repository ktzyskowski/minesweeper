package minesweeper;

/**
 * Class to implement a Coordinate object
 */
public class Coord {

    public double x, y;

    public Coord(double row, double col)
    {
        this.x = row;
        this.y = col;
    }

    public boolean equals(Coord other)
    {
        return (this.x == other.x && this.y == other.y);
    }

}
