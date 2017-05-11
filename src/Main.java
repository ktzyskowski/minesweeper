import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private GridPane game;

    public void initialize() {
        // ... clear old game && make new one
        game.getChildren().clear();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                Tile tile = new Tile();
                game.add(tile, c, r);
            }
        }

        // ... testing purposes
        game.add(new Mine(), 0, 0);
        game.add(new NumberTile(1), 1, 0);
    }

    public void start() {
        try {
            rows = Integer.parseInt(rowInput.getCharacters().toString());
            columns = Integer.parseInt(colInput.getCharacters().toString());
            mines = Integer.parseInt(mineInput.getCharacters().toString());
        } catch (Exception exception) {
            errormsg.setText("Please enter a number");
            return;
        }

        // ... check for valid arguments
        if (inBounds(8, 16, rows) && inBounds(8, 16, columns) && inBounds(12, 80, mines)) {
            // ... start game
            errormsg.setText("");

            initialize();

        } else {
            errormsg.setText("A number is not in the proper range");
            return;
        }

        System.out.println(rows + " " + columns + " " + mines);
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
            Label rowsLabel = new Label("Rows:");
            Label colsLabel = new Label("Columns:");
            Label minesLabel = new Label("Mines:");
            rowInput = new TextField();
            rowInput.setPromptText("8-16");
            rowInput.setMaxWidth(50);
            colInput = new TextField();
            colInput.setPromptText("8-16");
            colInput.setMaxWidth(50);
            mineInput = new TextField();
            mineInput.setPromptText("12-80");
            mineInput.setMaxWidth(60);
        bar.getChildren().addAll(startGame, rowsLabel, rowInput, colsLabel, colInput, minesLabel, mineInput);

        // ... game
        game = new GridPane();
            game.setStyle("-fx-background-color: #C0C0C0;");
            game.setAlignment(Pos.CENTER);
            game.setHgap(1);
            game.setVgap(1);

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
        window.setTitle("Minesweeper - dangreco & ktzyskowski");
        window.setResizable(false);
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

    public Tile getTile(int row, int column) {
        for (Node t : game.getChildren()) {
            if (game.getRowIndex(t) == row && game.getColumnIndex(t) == column) {
                return (Tile) t;
            }
        }
        return null;
    }

}
