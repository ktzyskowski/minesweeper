import javafx.scene.paint.Color;

public class Mine extends Tile {
    public Mine(int row, int col) {
        super(row, col);
    }

    @Override
    public void interact() {
        this.setFill(Color.RED);
    }
}
