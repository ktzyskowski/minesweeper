import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Main extends Application {

  // TODO: Add methods for determining game size / add different scene for game setup.

  @Override
  public void start(Stage primaryStage)
  {
    Pane ssRoot = new Pane();
    SetupScene mainSetup = new SetupScene(ssRoot);

    primaryStage.setTitle("Minesweeper");
    primaryStage.setScene(mainSetup);
    primaryStage.show();

    mainSetup.getStartButton().setOnAction(actionHandler -> {
      int[] boardSize = mainSetup.getBoardSize();
      if (boardSize != null){
        // Do something
      }
    });

  }

  public static void main(String[] args) {
    launch(args);
  }

}
