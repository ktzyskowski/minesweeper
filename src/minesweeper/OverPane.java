package minesweeper;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class OverPane extends GridPane{
    private int rawScore;
    private Text result, score, minesFlagged;
    private ListView<String> leaderboard;
    private TextField leaderboardName;
    private Button leaderboardSubmit, playAgain;
    private Leaderboard board;

    public OverPane(String result, int score, int minesFlagged)
    {
        super();
        this.rawScore = score;
        this.result = new Text(result);
        this.score = new Text(Integer.toString(score));
        this.minesFlagged = new Text(Integer.toString(minesFlagged));
        this.leaderboard = new ListView<>();
        this.leaderboard.getItems().add(0, "--- LEADERBOARD ---");
        this.playAgain = new Button("Play Again");
        this.leaderboardName = new TextField("John Doe");
        this.leaderboardSubmit = new Button("Submit");

        leaderboard.setMaxHeight(100.0);
        leaderboard.setMaxWidth(175.0);

        leaderboardSubmit.setOnMouseClicked(mouseEvent -> export());

        fillLeaderboard();

        this.add(this.result,0,0);
        this.add(this.score,  0,1);
        this.add(this.minesFlagged, 0,2);
        this.add(leaderboard, 0,3);
        this.add(leaderboardName,0,4);
        this.add(leaderboardSubmit, 0, 5);
        this.add(playAgain, 0,6);



    }

    public void export()
    {
        board.getScores().add(new Leaderboard.Score(leaderboardName.getText(), rawScore));
        Leaderboard.saveLeaderboard(board);
    }

    public void fillLeaderboard()
    {
        board = Leaderboard.loadLeaderboard();
        if (board != null){
            ArrayList<Leaderboard.Score> scores = board.getScores();
            if (scores != null){
                for (Leaderboard.Score s : scores){
                    if (s != null){
                        System.out.println(s.toString());
                        leaderboard.getItems().add(s.toString());
                    }
                }
            }
        }
    }

}
