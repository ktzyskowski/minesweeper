import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private int rows;
    private int columns;
    private int mines;

    private TextField rowInput;
    private TextField colInput;
    private TextField mineInput;

    private Label errormsg;

    public void start() {
        try {
            rows = Integer.parseInt(rowInput.getCharacters().toString());
            columns = Integer.parseInt(colInput.getCharacters().toString());
            mines = Integer.parseInt(mineInput.getCharacters().toString());
        } catch (Exception exception) {
            errormsg.setText("Please enter a number");
            return;
        }

        System.out.println(rows + " " + columns + " " + mines);
        // ... check for valid arguments
        if (inBounds(8,40, rows) &&
                inBounds(8,40, columns) &&
                inBounds(12,100, mines)) {
            errormsg.setText("Game started");
            // ... start game
        } else {
            errormsg.setText("A number is not in the proper range");
            return;
        }
    }

    public static void main(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {
        // ... bar
        HBox bar = new HBox();
            bar.setPadding(new Insets(10, 10, 10, 10));
            bar.setSpacing(10);
            bar.setAlignment(Pos.CENTER);
            Button startGame = new Button("Start");
            startGame.setOnAction(event -> start());
            Label rows = new Label("Rows:");
            Label cols = new Label("Columns:");
            Label bombs = new Label("Mines:");
            rowInput = new TextField();
            rowInput.setPromptText("8-40");
            rowInput.setMaxWidth(50);
            colInput = new TextField();
            colInput.setPromptText("8-40");
            colInput.setMaxWidth(50);
            mineInput = new TextField();
            mineInput.setPromptText("12-100");
            mineInput.setMaxWidth(60);
        bar.getChildren().addAll(startGame, rows, rowInput, cols, colInput, bombs, mineInput);

        // ... game
        GridPane game = new GridPane();
        game.setStyle("-fx-background-color: #C0C0C0;");
        // ... error message
        HBox error = new HBox();
        error.setPadding(new Insets(10,10,10,10));
        error.setAlignment(Pos.CENTER);
            errormsg = new Label("");
        error.getChildren().addAll(errormsg);

        // ... linking everything together
        BorderPane main = new BorderPane();
        main.setTop(bar);
        main.setCenter(game);
        main.setBottom(error);

        Scene application = new Scene(main, 600, 600);
        window.setScene(application);
        window.show();
    }

    // ... helper methods
    private boolean inBounds(int lowerBound, int upperBound, int number) {
        if (number > upperBound || number < lowerBound) {
            return false;
        } else {
            return true;
        }
    }

}