import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class Tile extends Rectangle {
    private boolean depressed;
    private int row, col;

    public Tile(int row, int col)
    {
        super();
        this.row = row;
        this.col = col;
        this.setWidth(15);
        this.setHeight(15);
        this.depressed = false;
        this.setFill(Color.LIGHTGRAY);
        this.setStrokeWidth(1.0);
        this.setStroke(Color.BLACK);

        this.setOnMouseClicked(mouseEvent -> interact());


    }

    public int [] getPos() {
        int [] x = {row, col};
        return x;
    }

    public void interact() {
        if (!depressed) {
            depressed = true;
            this.setFill(Color.GRAY);
        }
    }

}