import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MinesweeperScene extends Scene {

  private Pane root;
  public static final int SCENEWIDTH = 500;
  public static final int SCENEHEIGHT = 500;

  private MinesweeperGame game;

  /**
   * @param root  Root pane for the scene
   * @param game  Main game object
   */


  public MinesweeperScene(Pane root, MinesweeperGame game)
  {
    super(root, SCENEWIDTH, SCENEHEIGHT);
    this.root = root;
    this.game = game;
  }

  public void translateIntoView()
  {

  }






}
