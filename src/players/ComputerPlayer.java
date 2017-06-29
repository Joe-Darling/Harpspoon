package players;

import cards.Card;

/**
 * Created by Joe on 6/29/2017.
 * The abstract class used by all computer players
 */
public abstract class ComputerPlayer extends Player{
    private int gamesToPlay;

    public ComputerPlayer(String name, int gamesToPlay){
        super(name);
        this.gamesToPlay = gamesToPlay;
    }

    public boolean continuePlaying() {
        gamesToPlay--;
        return gamesToPlay == -1 || gamesToPlay != 0;
    }

    public abstract Card playACard();
}
