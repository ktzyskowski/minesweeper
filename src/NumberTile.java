import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;

public class NumberTile extends Tile {

    private int number;
    private Text numText;

    public NumberTile(int number, int row, int col)
    {
        super(row, col);
        this.number = number;
        numText = new Text(Integer.toString(number));
        changeColor();
        numText.setStrokeWidth(2.0);
        this.getChildren().add(numText);
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    @Override
    public void interact()
    {
        this.getSelf().setFill(Color.GRAY);
    }

    public void changeColor(){
        switch(number){
            case 1:
                numText.setFill(Color.BLUE);
                break;
            case 2:
                numText.setFill(Color.GREEN);
                break;
            case 3:
                numText.setFill(Color.RED);
                break;
            case 4:
                numText.setFill(Color.NAVY);
                break;
            case 5:
                numText.setFill(Color.MAROON);
                break;
            case 6:
                numText.setFill(Color.TURQUOISE);
                break;
            case 7:
                numText.setFill(Color.BLACK);
                break;
            case 8:
                numText.setFill(Color.DARKGRAY);
                break;

        }
    }

}
