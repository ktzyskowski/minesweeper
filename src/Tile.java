import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {

    private Rectangle self;
    private boolean clicked;

    public Tile() {
        this.self = new Rectangle(25, 25, Color.WHITESMOKE);
        this.self.setOnMouseClicked(event -> interact());
        this.self.setArcHeight(6);
        this.self.setArcWidth(6);
        this.getChildren().add(self);

        // ... testing purposes
        this.interact();
    }

    public void interact() {
        if (!getClicked()) {
            setClicked(true);
            this.getSelf().setFill(Color.LIGHTGRAY);
        }
    }

    // ... getters / setters
    public boolean getClicked() { return clicked; }
    public void setClicked(boolean click) { this.clicked = click; }

    public Rectangle getSelf() { return self; }

    @Override
    public String toString()
    {
        return "T ";
    }

}