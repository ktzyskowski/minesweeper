package minesweeper;

import javafx.scene.paint.Color;

/**
 * Class to implement a minesweeper.Mine tile
 */
public class Mine extends Tile {

    @Override
    public void show()
    {
        setClicked(true);
        this.getSelf().setFill(Color.TOMATO);
        this.getSelf().setStroke(Color.TOMATO);
    }

    @Override
    public String toString()
    {
        return "M ";
    }

}