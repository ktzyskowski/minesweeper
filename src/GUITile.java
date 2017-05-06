import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GUITile extends Pane{

    private int row, col;
    private boolean depressed;
    public static final Background HAPPYBACKGROUND = new Background(new BackgroundFill(Color.GRAY, null, null));
    public static final Background DEPRESSEDBACKGROUND = new Background(new BackgroundFill(Color.DARKGRAY, null, null));

    /**
     * @param row   The row position of Tile
     * @param col   The column position of Tile
     */
    public GUITile(int row, int col)
    {
        this.row = row;
        this.col = col;
        this.depressed = false;
        this.setBackground(HAPPYBACKGROUND);

        this.setOnMouseClicked(mouseEvent -> {
            if (!depressed){
                depressed = true;
                this.setBackground(DEPRESSEDBACKGROUND);
            }
        });

    }

}