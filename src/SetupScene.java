import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class SetupScene extends Scene {
  private Pane root;
  public static final int SCENEWIDTH = 500;
  public static final int SCENEHEIGHT = 500;

  public static final Font inputFont = new Font("Helvetica", 60);

  // Labels
  private Label titleL, numRowsL, numColsL, developersL, errorMSG;

  // Two main TextFields
  private TextField numRowsI, numColsI;

  // Start Button
  private Button startButton;

  public SetupScene(Pane root)
  {
    super(root, SetupScene.SCENEWIDTH, SetupScene.SCENEHEIGHT);
    this.root = root;
    setupLayout();
  }


  // WARNING: UGLY CODE
  public void setupLayout()
  {
    titleL = new Label("Minesweeper v.0.1");
    titleL.setFont(new Font("Helvetica", 40.0));
    titleL.setLayoutX(60);

    numRowsL = new Label("Rows");
    numRowsL.setFont(SetupScene.inputFont);
    numRowsL.setLayoutX(35);
    numRowsL.setLayoutY(75);

    numColsL = new Label("Cols");
    numColsL.setFont(SetupScene.inputFont);
    numColsL.setLayoutX(260);
    numColsL.setLayoutY(75);

    developersL = new Label("Developed by Kevin Zyskowski and Daniel Greco");
    developersL.setFont(new Font("Helvetica", 12));
    developersL.setLayoutX(100);
    developersL.setLayoutY(50);

    errorMSG = new Label("");
    errorMSG.setFont(new Font("Helvetica", 16));
    errorMSG.setLayoutY(475);
    errorMSG.setLayoutX(50);

    numRowsI = new TextField("50");
    numRowsI.setLayoutX(35);
    numRowsI.setLayoutY(150);
    numRowsI.setMinHeight(100);
    numRowsI.setMaxWidth(200);
    numRowsI.setFont(SetupScene.inputFont);

    numColsI = new TextField("50");
    numColsI.setLayoutX(260);
    numColsI.setLayoutY(150);
    numColsI.setMinHeight(100);
    numColsI.setMaxWidth(200);
    numColsI.setFont(SetupScene.inputFont);

    startButton = new Button("Start Game");
    startButton.setMinWidth(400);
    startButton.setMinHeight(100);
    startButton.setFont(SetupScene.inputFont);
    startButton.setLayoutX(35);
    startButton.setLayoutY(300);

    root.getChildren().addAll(titleL, developersL, numRowsL, numColsL, numRowsI, numColsI, startButton, errorMSG);
  }

  public int[] getBoardSize()
  {

    try {
      int numRows = Integer.parseInt(numRowsI.getText());
      int numCols = Integer.parseInt(numColsI.getText());
      int[] toRet = { numRows , numCols };
      dispError("");
      return toRet;
    } catch (NumberFormatException e){
      dispError("Uh oh! Please make sure those are real numbers.");
    }

    return null;

  }

  public Button getStartButton()
  {
    return startButton;
  }

  public void dispError(String error)
  {
    errorMSG.setText(error);
  }

}
