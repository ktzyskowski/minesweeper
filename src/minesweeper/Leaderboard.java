package minesweeper;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Leaderboard {

    public static class Score {

        private String name;
        private int score;

        public Score(String name, int score)
        {
            this.name = name;
            this.score = score;
        }

        public String getName()
        {
            return name;
        }

        public int getScore()
        {
            return score;
        }

        public static Score parseScore(String line)
        {
            String[] infoArr = line.split("\\|");
            if (infoArr.length < 2) {
                System.out.println("< 2");
                return null;
            }
            int score = -1;
            try {
                score = Integer.parseInt(infoArr[1]);
            } catch (Exception e) {
                System.out.println("# e : " + infoArr[1]);
                return null;
            }
            return new Score(infoArr[0], score);
        }

        @Override
        public String toString()
        {
            return name + "|" + score;
        }


    }

    private ArrayList<Score> board;

    public Leaderboard(ArrayList<Score> board)
    {
        this.board = board;
    }

    public ArrayList<Score> getScores()
    {
        return board;
    }

    public static Leaderboard loadLeaderboard()
    {
        ArrayList<Score> scores = new ArrayList<>();
        File input = new File("/home/dan/Desktop/minesweeper/src/minesweeper/leaderboard.txt");

        try {
            FileReader inputReader = new FileReader(input);
            Scanner inputScanner = new Scanner(inputReader);
            while (inputScanner.hasNextLine()){
                scores.add(Score.parseScore(inputScanner.nextLine().trim()));
            }
            System.out.println("S");
            return new Leaderboard(scores);
        } catch (Exception e) {
            System.out.println("F");
            return null;
        }
    }

    public static void saveLeaderboard(Leaderboard board)
    {
        File output = new File("/home/dan/Desktop/minesweeper/src/minesweeper/leaderboard.txt");

        try {
            FileWriter outputWriter = new FileWriter(output);
            for (Score s : board.getScores()){
                outputWriter.write(s.toString());
            }
            outputWriter.close();
            System.out.println("written to file");
        } catch (Exception e){
            return;
        }
    }

}
