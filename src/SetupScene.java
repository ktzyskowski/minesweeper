import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class SetupScene extends Scene {

  private Pane root;
  public static final int SCENEWIDTH = 500;
  public static final int SCENEHEIGHT = 500;

  // Labels
  private Label titleL, numRowsL, numColsL, developersL;

  public SetupScene()
  {
    super(null, SetupScene.SCENEWIDTH, SetupScene.SCENEHEIGHT);
    root = new Pane();
    this.setRoot(root);

    titleL = new Label("Minesweeper v.0.1");
    numRowsL = new Label("Number of rows:");
    numColsL = new Label("Numver of cols:");
    developersL = new Label("Developed by Kevin Zyskowski and Daniel Greco");

    root.addAll

  }

}
