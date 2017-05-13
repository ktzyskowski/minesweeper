import javafx.scene.paint.Color;

public class Mine extends Tile {

    @Override
    public void interact() {
        if (!getClicked()) {
            show();
            reveal();
        }
    }

    @Override
    public void show()
    {
        setClicked(true);
        this.getSelf().setFill(Color.TOMATO);
    }

    @Override
    public String toString()
    {
        return "M ";
    }

}