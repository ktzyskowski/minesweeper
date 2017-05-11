import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {

    private Rectangle self;
    private boolean clicked;

    public Tile() {
        this.self = new Rectangle(12, 12, Color.RED);
        this.self.setOnMouseClicked(event -> interact());
        this.getChildren().add(self);
    }

    public void interact() {
        if (!getClicked()) {
            clicked = true;
            this.self.setFill(Color.LIGHTGRAY);
        }
    }

    // ... getters / setters
    public boolean getClicked() { return clicked; }
    public Rectangle getSelf() { return self; }

}