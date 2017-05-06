import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GUITile extends Rectangle{

    private int row, col;
    private Tile me;
    private boolean depressed;

    /**
     * @param me    The Tile that this represents
     * @param row   The row position of Tile
     * @param col   The column position of Tile
     */
    public GUITile(Tile me, int row, int col)
    {
        super();
        this.setWidth(15);
        this.setHeight(15);
        this.me = me;
        this.row = row;
        this.col = col;
        this.depressed = false;
        this.setFill(Color.LIGHTGRAY);
        this.setStrokeWidth(1.0);
        this.setStroke(Color.BLACK);

        this.setOnMouseClicked(mouseEvent -> {
            if (!depressed){
                depressed = true;
                this.setFill(Color.GRAY);
            }
        });

    }

}