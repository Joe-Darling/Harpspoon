package players;

import cards.Card;
import cards.CommonCard;

/**
 * Created by Joe on 6/21/2017.
 */
public class Mage extends Player {

    private int gamesToPlay;

    public Mage(int gamesToPlay){
        super("Mage");
        this.gamesToPlay = gamesToPlay;
    }

    public boolean continuePlaying() {
        if(gamesToPlay == -1)
            return true;
        else{
            if(gamesToPlay == 0)
                return false;
            else{
                gamesToPlay -= 1;
                return true;
            }
        }
    }

    public Card playACard() {
        Card bestPlay = null;
        for(Card card : getHand()){ // For each card in our hand
            if(bestPlay == null){ // If we haven't found a legal card yet
                if(card.getCost() <= getCurrMana()) // Check if any card has legal mana cost
                    bestPlay = card; // and set it as our best
            }
            else{ // Otherwise set it to the highest cost card that is within our current mana
                if(card.getCost() > bestPlay.getCost() && card.getCost() <= getCurrMana())
                    bestPlay = card;
                // In case of a tie in mana the higher attack wins.
                else if(card.getCost() == bestPlay.getCost() && card.getBaseAttack() > bestPlay.getBaseAttack())
                    bestPlay = card;
            }
        }
        if(bestPlay == null)
            System.out.println(getName() + " passes their turn.\n");
        return bestPlay;
    }
}
