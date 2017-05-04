import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

public class SetupScene extends Scene {

  private Pane root;
  public static final int SCENEWIDTH = 500;
  public static final int SCENEHEIGHT = 500;

  public SetupScene()
  {
    super(null, SetupScene.SCENEWIDTH, SetupScene.SCENEHEIGHT);
    root = new Pane();
    this.setRoot(root);
  }

}
