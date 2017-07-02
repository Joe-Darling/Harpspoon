package game;

import players.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 6/29/2017.
 * Class used to create a report of what happens in game for grading
 */
public class Reporter {
    private List<String> report;

    public Reporter(){
        report = new ArrayList<>();
    }

    public List<String> getReport(){
        return report;
    }

    public void printReport(){
        for(String line : report)
            System.out.println(line);
    }

    public void newReport(Harpspoon game){
        report = new ArrayList<>();
        report.add("NEW GAME STARTED");
        addPlayerStats(game);
    }

    public void addPlayerStats(Harpspoon game){
        report.add(game.getPlayer1().playerPrint(game.getPlayer1TotalScore()));
        report.add(game.getPlayer2().playerPrint(game.getPlayer2TotalScore()));
    }

    public void addStringToReport(String s){
        report.add(s);
    }
}
