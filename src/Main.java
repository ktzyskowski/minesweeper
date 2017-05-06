import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Main extends Application {

  private Pane ssRoot, gameRoot;
  private SetupScene mainSetup;
  private MinesweeperGame game;
  private MinesweeperScene minesweeperScene;

  @Override
  public void start(Stage primaryStage)
  {
    ssRoot = new Pane();
    mainSetup = new SetupScene(ssRoot);

    primaryStage.setTitle("Minesweeper");
    primaryStage.setScene(mainSetup);
    primaryStage.setResizable(false);
    primaryStage.show();

    mainSetup.getStartButton().setOnAction(actionHandler -> {
      int[] boardSize = mainSetup.getBoardSize();
      if (boardSize != null){
        int bombs = (int)(.15 * boardSize[0] * boardSize[1]);
        game = new MinesweeperGame(boardSize[0], boardSize[1], bombs);
        gameRoot = new Pane();
        minesweeperScene = new MinesweeperScene(gameRoot, game, boardSize[0], boardSize[1]);
        primaryStage.setScene(minesweeperScene);
      }
    });

  }

  public static void main(String[] args) {
    launch(args);
  }

}
