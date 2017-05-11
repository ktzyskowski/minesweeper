import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class NumberTile extends Tile {

    private Label number;

    public NumberTile(int number) {
        super();
        this.number = new Label(Integer.toString(number));
        this.number.setStyle("-fx-font: 10 arial;");
        this.number.setTextFill(Color.WHITE);
        this.number.setOnMouseClicked(event -> interact());
        this.getChildren().add(this.number);
    }

    @Override
    public void interact() {
        if (!getClicked()) {
            setClicked(true);
            this.getSelf().setFill(Color.SKYBLUE);
        }
    }

}