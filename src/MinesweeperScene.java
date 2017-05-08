import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MinesweeperScene extends Scene {

  private Pane root;
  private GridPane tilesPane;
  private int sceneWidth;
  private int sceneHeight;

  private MinesweeperGame game;

  /**
   * @param root  Root pane for the scene
   * @param game  Main game object
   */


  public MinesweeperScene(Pane root, MinesweeperGame game, int width, int height)
  {
    super(root, width * (16), height * 16);
    this.root = root;
    this.game = game;
    tilesPane = new GridPane();
    translateIntoView();
    this.root.getChildren().add(tilesPane);
  }

  public void translateIntoView()
  {
    Tile[][] tiles = game.getBoard();
    for (int i = 0; i < tiles.length; ++i){
      for (int j = 0; j < tiles[i].length; ++j){
        System.out.println(i + " " + j);
        if (tiles[i][j] instanceof Mine) {
          tilesPane.add(new Mine(i, j), i, j);
        } else {
          tilesPane.add(new Tile(i, j), i, j);
        }
      }
    }
  }






}
