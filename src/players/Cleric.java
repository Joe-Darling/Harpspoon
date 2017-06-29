package players;

import cards.Card;

/**
 * Created by Joe on 6/29/2017.
 */
public class Cleric extends ComputerPlayer{

    public Cleric(int gamesToPlay){
        super("Cleric", gamesToPlay);
    }

    public Card playACard() {
        Card bestPlay = null;
        for(Card card : getHand()){ // For each card in our hand
            if(bestPlay == null){ // If we haven't found a legal card yet
                if(card.getCost() <= getCurrMana()) // Check if any card has legal mana cost
                    bestPlay = card; // and set it as our best
            }
            else{ // Otherwise set it to the highest health card that is within our current mana
                if(card.getBaseHealth() > bestPlay.getBaseHealth() && card.getCost() <= getCurrMana())
                    bestPlay = card;
                    // In case of a tie in health the lower cost wins.
                else if(card.getCost() == bestPlay.getCost() && card.getCost() < bestPlay.getCost())
                    bestPlay = card;
            }
        }
        if(bestPlay == null)
            System.out.println(getName() + " passes their turn.\n");
        return bestPlay;
    }
}
