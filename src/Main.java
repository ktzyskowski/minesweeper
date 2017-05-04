import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Main extends Application {

  // TODO: Add methods for determining game size / add different scence for game setup.

  @Override
  public void start(Stage primaryStage)
  {

    SetupScene mainSetup = new SetupScene();

    primaryStage.setTitle("Minesweeper")
    primaryStage.setScene(mainSetup);
    primaryStage.show();
  }

}
