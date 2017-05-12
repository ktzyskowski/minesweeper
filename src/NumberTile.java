import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class NumberTile extends Tile {

    private Label number;
    private Color color;

    public NumberTile(int number) {
        super();
        this.number = new Label(Integer.toString(number));
        this.number.setStyle("-fx-font: 16 courier;");
        this.number.setOnMouseClicked(event -> interact());
        this.getChildren().add(this.number);

        switch(number) {
            case 1:
                color = Color.SKYBLUE;
                break;
            case 2:
                color = Color.LAWNGREEN;
                break;
            case 3:
                color = Color.ORANGERED;
                break;
            case 4:
                color = Color.DEEPSKYBLUE;
                break;
            case 5:
                color = Color.MAROON;
                break;
            case 6:
                color = Color.TURQUOISE;
                break;
            case 7:
                color = Color.BLACK;
                break;
            case 8:
                color = Color.SLATEGRAY;
                break;
            default:
                color = Color.WHITE;
                break;
        }

        this.number.setTextFill(Color.WHITESMOKE);
    }

    @Override
    public void interact() {
        if (!getClicked()) {
            super.interact();
            this.getSelf().setFill(color);
        }
    }

    @Override
    public String toString()
    {
        return number + " ";
    }

}