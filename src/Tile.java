import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class Tile extends StackPane {
    private boolean depressed;
    private int row, col;
    private Rectangle self;

    public Tile(int row, int col)
    {
        super();
        self = new Rectangle();
        this.getChildren().add(self);
        this.row = row;
        this.col = col;
        self.setWidth(20);
        self.setHeight(20);
        this.depressed = false;
        self.setFill(Color.LIGHTGRAY);
        self.setStrokeWidth(1.0);
        self.setStroke(Color.BLACK);

        this.interact();

        this.setOnMouseClicked(mouseEvent -> interact());


    }

    public Rectangle getSelf()
    {
        return self;
    }

    public int [] getPos() {
        int [] x = {row, col};
        return x;
    }

    public void interact() {
        if (!depressed) {
            depressed = true;
            self.setFill(Color.GRAY);
            //HashMap<int[], Tile> map = MinesweeperScene.game.getReveals(this.row, this.col);
//            /for ()
        }
    }

}