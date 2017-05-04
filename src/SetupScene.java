import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

public class SetupScene extends Scene {

  private Pane root = new Pane();
  public static final int SCENEWIDTH = 500;
  public static final int SCENEHEIGHT = 500;

  public SetupScene()
  {
    super(root, SetupScene.SCENEWIDTH, SetupScene.SCENEHEIGHT);
  }

  public Pane getRoot()
  {
    return root;
  }

}
