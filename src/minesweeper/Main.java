package minesweeper;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Main application class
 */
public class Main extends Application {

    // Static variables

    public static boolean dragOver;

    public static Minesweeper minesweeperGame;

    // Instance variables

    private int rows, columns, mines;

    private Coord origin, result;

    private Stage window;
    private TextField rowInput, colInput, mineInput;
    private Label errormsg, rowsLabel, colsLabel, minesLabel;
    private GridPane game;
    private Scene application;
    private StackPane barStack;
    private Rectangle barBackground;
    private HBox bar, error;
    private Button startGame;
    private BorderPane mainPane;


    /**
     * minesweeper.Main launch of application
     * @param args Specified arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Method to start the application
     * @param window The primary Stage
     */
    @Override
    public void start(Stage window)
    {
        this.window = window;
        origin = new Coord(0.0,0.0);
        result = new Coord(0.0,0.0);

        // Creating + adding GUI nodes to stage
        createBar();
        createGame();
        createErrorMessageArea();
        linkEverythingTogether();

        // Handling scrolling on GridPane
        game.setOnScroll(scrollEvent -> zoom(scrollEvent.getDeltaY()));

        // Handling click on GridPane
        game.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent));

        // Handling mouse release on GridPane
        game.setOnMouseReleased(mouseEvent -> application.setCursor(Cursor.DEFAULT));

        // Handling mouse drag on GridPane
        game.setOnMouseDragged(mouseDragEvent -> mouseDragged(mouseDragEvent));

        // Handling end of mouse drag on GridPane
        game.setOnMouseDragReleased(mouseDragEvent -> dragOver = true);
    }



    /*      -------------------------
            --- IMPORTANT METHODS ---
            -------------------------
    */


    /**
     *  Creates and displays a new game.
     */
    public void initialize()
    {
        // Clear old game & make new one
        game.getChildren().clear();
        minesweeperGame = new Minesweeper(rows, columns, mines);
        Tile[][] internalBoard = minesweeperGame.getBoard();
        // Populates game from internalBoard
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                game.add(internalBoard[r][c], c, r);
                if (internalBoard[r][c] instanceof Mine) {
                    internalBoard[r][c].setOnMouseClicked(event -> {
                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                            endGame("Defeat!");
                        }
                    });
                }
            }
        }
    }

    /**
     * Method that is called when a mine is clicked by
     * user. Ends the game.
     */
    public void endGame(String res)
    {
        System.out.println(res);
        window.hide();
        OverPane gameOver = new OverPane(res, Minesweeper.score, amountOfFlaggedMines());
        gameOver.getPlayAgainButton().setOnMouseClicked(mouseEvent -> {
            start(window);
        });
        window.setScene(new Scene(gameOver));
        window.show();

    }

    /**
     *  Method that is called when start button is clicked.
     *  First checks for validity of arguments, then
     *  calls initialize() with new params if arguments
     *  are valid and displays error message otherwise.
     */
    public void start()
    {
        // Get parameters to use in initialize()
        try {
            rows = Integer.parseInt(rowInput.getCharacters().toString());
            columns = Integer.parseInt(colInput.getCharacters().toString());
            mines = Integer.parseInt(mineInput.getCharacters().toString());
        } catch (Exception exception) {
            errormsg.setText("Please enter a number");
            return;
        }

        // Check for valid arguments
        if (inRange(8, 20, rows) && inRange(8, 20, columns) && inRange(12, 100, mines) && mines < rows * columns) {
            errormsg.setText("");
            // Start game
            initialize();
        } else {
            // Display error
            errormsg.setText("A number is not in the proper range");
            return;
        }

        System.out.println(rows + " " + columns + " " + mines);
    }


    /*      ------------------------------
            --- HELPER / OTHER METHODS ---
            ------------------------------
    */


    /**
     * Helper method to create main top bar (HBox)
     */
    public void createBar()
    {
        // Initialize main StackPane
        barStack = new StackPane();

        // Initialize the background to the bar
        barBackground = new Rectangle(800, 60, new Color(.06, .34, 1, .75));

        // Initialize + style the bar
        bar = new HBox();
        bar.setPadding(new Insets(10, 10, 10, 10));
        bar.setSpacing(10);
        bar.setAlignment(Pos.CENTER);

        // Creating / styling fields

            // Start game button
            startGame = new Button("Start");
            startGame.setOnAction(event -> start());

            // Labels
            rowsLabel = new Label("Rows:");
            colsLabel = new Label("Columns:");
            minesLabel = new Label("Mines:");

            // Row input
            rowInput = new TextField();
            rowInput.setPromptText("8-20");
            rowInput.setMaxWidth(50);

            // Column input
            colInput = new TextField();
            colInput.setPromptText("8-20");
            colInput.setMaxWidth(50);

            // minesweeper.Mine input
            mineInput = new TextField();
            mineInput.setPromptText("12-100");
            mineInput.setMaxWidth(60);

        // Adding fields + labels to bar
        bar.getChildren().addAll(startGame, rowsLabel, rowInput, colsLabel, colInput, minesLabel, mineInput);

        // Adding bar + background to main StackPane
        barStack.getChildren().addAll(barBackground, bar);
    }

    /**
     * Helper method to create / style the game (GridPane)
     */
    public void createGame()
    {
        game = new GridPane();
        game.setStyle("-fx-background-color: transparent;");
        game.setAlignment(Pos.CENTER);
        game.setHgap(1);
        game.setVgap(1);
    }

    /**
     * Helper method to create / style error message area (HBox)
     */
    public void createErrorMessageArea()
    {
        error = new HBox();
        error.setPadding(new Insets(10, 10, 10, 10));
        error.setAlignment(Pos.CENTER);
        errormsg = new Label("");
        error.getChildren().addAll(errormsg);
    }

    /**
     * Helper method to add every GUI node to the stage
     */
    public void linkEverythingTogether()
    {
        // Initialize + fill main BorderPane
        mainPane = new BorderPane();
        mainPane.setCenter(game);
        mainPane.setBottom(error);
        mainPane.setTop(barStack);

        // Initialize main scene w/ main BorderPane
        application = new Scene(mainPane, 800, 600);

        // Finish setting main stage config.
        window.setScene(application);
        window.setTitle("minesweeper.Minesweeper - dangreco & ktzyskowski");
        window.setResizable(false);
        window.show();
    }

    /**
     * Helper method to handle dragging of GridPane;
     * calculates new translation based on current translation
     * + mouseLocation relative to origin.
     * @param e The triggered MouseEvent
     */
    public void mouseDragged(MouseEvent e)
    {
        result.x = e.getX();
        result.y = e.getY();
        game.setTranslateX(game.getTranslateX() + (result.x - origin.x));
        game.setTranslateY(game.getTranslateY() + (result.y - origin.y));
        dragOver = false;
    }

    /**
     * Helper method to handle mousePressed action;
     * sets origin of click + changes cursor type
     * @param e The triggered MouseEvent
     */
    public void mousePressed(MouseEvent e)
    {
        origin.x = e.getX();
        origin.y = e.getY();
        application.setCursor(Cursor.CLOSED_HAND);
    }

    /**
     * Helper method to change scale of GridPane when zoomed
     * > Delta is changed to either -1.0 or 1.0
     * > Tests for either one
     * > Checks for zoom bounds
     * > If satisfies, changes scale according to first calc.
     * @param delta Y-Delta of mouse scroll; either -40.0 (down) or 40.0 (up)
     */
    public void zoom(double delta)
    {
        double scale = game.getScaleX() + delta / 40.0 * .2;
        if (delta < 0) {
            if (game.getScaleX() > 0.3) {
                game.setScaleX(scale);
                game.setScaleY(scale);
            }
        } else {
            if (game.getScaleY() < 10.0) {
                game.setScaleX(scale);
                game.setScaleY(scale);
            }
        }
    }

    /**
     * Checks to see if a number is within a specific range defined by
     * [lowerBound, upperBound].
     * @param lowerBound Lower bound of the range - inclusive
     * @param upperBound Upper bound of the range - inclusive
     * @param number Number being checked
     * @return True if in range; false if not in range
     */
    private boolean inRange(int lowerBound, int upperBound, int number)
    {
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
     * @param row Row of tile being searched for
     * @param column Column of tile being searched for
     * @return minesweeper.Tile at pos row, column; null if tile does not exist
     */
    public Tile getTile(int row, int column)
    {
        for (Node t: game.getChildren()) {
            if (game.getRowIndex(t) == row && game.getColumnIndex(t) == column) {
                return (Tile) t;
            }
        }
        return null;
    }

    /**
     * Helper method to return the current minesweeper.Minesweeper game
     * to whatever other class needs it
     * @return Current minesweeper.Minesweeper game
     */
    public static Minesweeper getMinesweeperGame()
    {
        return minesweeperGame;
    }


    public int amountOfFlaggedMines()
    {
        int flaggedCount = 0;
        for (Tile[] subArr : minesweeperGame.getBoard()){
            for (Tile t : subArr){
                if (t instanceof Mine && t.getFlagged())
                    ++flaggedCount;
            }
        }
        return flaggedCount;
    }

}