import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class NumberTile extends Tile {

    private Label number;
    private int num;
    private Color color;

    public NumberTile(int number) {
        super();
        this.num = number;
        this.number = new Label();
        this.number.setStyle("-fx-font: 16 courier;");
        //this.number.setOnMouseClicked(event -> interact());
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
                color = Color.VIOLET;
                break;
        }

        this.number.setTextFill(Color.WHITESMOKE);
    }

    @Override
    public void show(){
        setClicked(true);
        this.number.setText(Integer.toString(num));
        this.getSelf().setFill(color);
        this.getSelf().setStroke(color);
    }

    @Override
    public String toString()
    {
        return number + " ";
    }

}