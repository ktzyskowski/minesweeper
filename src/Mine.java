import javafx.scene.paint.Color;

public class Mine extends Tile {

    @Override
    public void interact() {
        if (!getClicked()) {
            setClicked(true);
            this.getSelf().setFill(Color.TOMATO);
        }
    }

    @Override
    public String toString()
    {
        return "M ";
    }

}