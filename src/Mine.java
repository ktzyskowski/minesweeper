
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Mine extends Tile {

    private ImageView imgView;

    public Mine(int row, int col) {
        super(row, col);
        imgView = new ImageView("mine.png");
        this.getSelf().setFill(Color.GRAY);
        this.getChildren().add(imgView);
    }

    @Override
    public void interact() {

    }
}
