package minesweeper;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class to implement a minesweeper.Tile
 */
public class Tile extends StackPane {

    private Rectangle self;
    private boolean clicked;
    private boolean flagged;

    public Tile() {
        this.self = new Rectangle(21, 21, Color.LIGHTSLATEGREY);
        this.self.setOnMouseClicked(event ->
        {
            MouseButton button = event.getButton();
            if (button.equals(MouseButton.PRIMARY)) {
                if (!Main.dragOver)
                    interact();
            }
            else if (button.equals(MouseButton.SECONDARY)) {
                flag();
            }
        });
        this.self.setArcHeight(6);
        this.self.setArcWidth(6);
        this.getChildren().add(self);
        this.flagged = false;
        this.self.setStrokeWidth(2.0);
        this.self.setStroke(Color.LIGHTSLATEGREY);

    }

    /**
     * Method to toggle the "flagging" of a minesweeper.Tile
     */
    public void flag() {
        if (!getClicked()) {
            setFlagged(!getFlagged());
            if (getFlagged()) {
                this.self.setStroke(Color.VIOLET);
            } else {
                this.self.setStroke(Color.LIGHTSLATEGREY);
            }
        }
    }

    /**
     * Method to handle left mouse click on minesweeper.Tile
     */
    public void interact() {
        if (!getClicked() && !getFlagged()) {
            show();
            reveal();
        }
    }

    /**
     * Method to show what's underneath
     */
    public void show() {
        setClicked(true);
        this.getSelf().setFill(Color.LIGHTGRAY);
        this.getSelf().setStroke(Color.LIGHTGRAY);
        Minesweeper.score += 10;
    }

    /**
     * Method to go about revealing calculated Tiles
     */
    public void reveal()
    {
        for (Tile t : Main.getMinesweeperGame().getReveals(this)){
            t.show();
        }
    }

    // Getters / setters
    public boolean getClicked() { return clicked; }
    public void setClicked(boolean click) { this.clicked = click; }

    public boolean getFlagged() { return flagged; }
    public void setFlagged(boolean flag) { this.flagged = flag; }

    public Rectangle getSelf() { return self; }

    @Override
    public String toString()
    {
        return "T ";
    }

}