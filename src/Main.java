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

    /**
     *  Creates and displays a new game.
     */
    public void initialize() {
        // ... clear old game && make new one
        game.getChildren().clear();
        Minesweeper minesweeper = new Minesweeper(rows, columns, mines);
        Tile[][] internalBoard = minesweeper.getBoard();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                game.add(internalBoard[r][c], c, r);
            }
        }

        /* ... testing purposes
        game.add(new Mine(), 0, 0);
        for (int i = 1; i < 9; i++) {
            game.add(new NumberTile(i), i, 0);
        }
        */
    }

    /**
     *  Method that is called when start button is clicked.
     *  First checks for validity of arguments, then
     *  calls initialize() with new params if arguments
     *  are valid and displays error message otherwise.
     */
    public void start() {
        // ... get parameters to use in initialize()
        try {
            rows = Integer.parseInt(rowInput.getCharacters().toString());
            columns = Integer.parseInt(colInput.getCharacters().toString());
            mines = Integer.parseInt(mineInput.getCharacters().toString());
        } catch (Exception exception) {
            errormsg.setText("Please enter a number");
            return;
        }

        // ... check for valid arguments
        if (inRange(8, 16, rows) && inRange(8, 16, columns) && inRange(12, 80, mines) && mines < rows * columns) {
            errormsg.setText("");
            // ... start game
            initialize();
        } else {
            // ... display error
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

    /**
     * Checks to see if a number is within a specific range defined by
     * [lowerBound, upperBound].
     * @param lowerBound lower bound of the range - inclusive
     * @param upperBound upper bound of the range - inclusive
     * @param number number being checked
     * @return true if in range; false if not in range
     */
    private boolean inRange(int lowerBound, int upperBound, int number) {
        if (number > upperBound || number < lowerBound) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Searches the game GridPane for a tile object and returns
     * the row & column that it appears in, or null if it does not
     * exist.
     * @param row row of tile being searched for
     * @param column column of tile being searched for
     * @return tile at pos row, column; null if tile does not exist
     */
    public Tile getTile(int row, int column) {
        for (Node t : game.getChildren()) {
            if (game.getRowIndex(t) == row && game.getColumnIndex(t) == column) {
                return (Tile) t;
            }
        }
        return null;
    }

}
