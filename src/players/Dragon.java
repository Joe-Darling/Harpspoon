package players;

import cards.Card;

/**
 * Created by Joe on 7/1/2017.
 */
public class Dragon extends ComputerPlayer{

    enum CardType{
        OFFENSIVE,
        DEFENSIVE
    }

    private CardType lastCardTypePlayed;
    private Card cardIWantToPlay;

    public Dragon(int gamesToPlay){
        super("Dragon", gamesToPlay);
    }

    /*
     * In my small amount of play testing I've found the strongest strategy is to play a strong defensive card
     * to tank the damage (since units are attacked in the order played) and then follow up with a strong offensive
     * Card to hopefully get in multiple attacks since it is protected by the defensive unit. So the best option would
     * be to alternate between offensive and defensive units. However what would make it stronger would be to play off
     * of the opponents board around units like Alec with undying, but that would require implementation change plus
     * I'm lazy. So this method will just alternate between defensive and offensive.
     */
    public Card playACard(){
        Card strongestPlayableCard = null; // Highest attack playable card in our hand
        Card bestPlay = null;

        // First thing we do is check to see if we have a legendary. Since that is always the strongest play
        // (well not always but without knowing what the opponent has it is the strongest thing we can do).
        if(getCurrMana() == 10) {
            for (Card card : getHand()) {
                if(card.getCost() == 10)
                    bestPlay = card;
            }
        }
        // Otherwise if we had setup a play earlier, we play that card here.
        else if(cardIWantToPlay != null){
            bestPlay = cardIWantToPlay;
            cardIWantToPlay = null;
        }
        else{
            // If the last card we played a defensive card, we look for the strongest offensive card we can play
            if(lastCardTypePlayed == CardType.DEFENSIVE){
                for(Card card : getHand()){ // For each card in our hand
                    if(strongestPlayableCard == null){ // If we haven't found a legal card yet
                        if(card.getCost() <= getCurrMana()) // Check if any card has legal mana cost
                            strongestPlayableCard = card; // and set it as our best
                    }
                    else{ // Otherwise set it to the highest attack card that is within our current mana
                        if(card.getBaseAttack() > strongestPlayableCard.getBaseAttack()
                                && card.getCost() <= getCurrMana())
                            strongestPlayableCard = card;
                            // In case of a tie in attack the higher health wins.
                        else if(card.getBaseAttack() == strongestPlayableCard.getBaseAttack()
                                && card.getBaseHealth() > strongestPlayableCard.getBaseHealth())
                            strongestPlayableCard = card;
                    }
                }
                //Next we check if we can play another card with this card this turn.

                if(strongestPlayableCard != null){
                    for (Card card : getHand()) { // For each card in our hand
                        if (bestPlay == null) { // If we haven't found a legal card yet
                            // Check if any card has legal mana cost after subtracting the strongest
                            if (card.getCost() <= getCurrMana() - strongestPlayableCard.getCost()) {
                                bestPlay = card; // and set it as our best for this play
                                // We then set the strongest card for next play
                                cardIWantToPlay = strongestPlayableCard;
                            }
                        } else { // Otherwise set it to the highest health card that is within remaining current mana
                            if (card.getBaseHealth() > bestPlay.getBaseHealth()
                                    && card.getCost() <= getCurrMana() - strongestPlayableCard.getCost())
                                bestPlay = card;
                                // In case of a tie in health the higher attack wins.
                            else if (card.getCost() == bestPlay.getCost()
                                    && card.getBaseAttack() > bestPlay.getBaseAttack())
                                bestPlay = card;
                        }
                    }
                }
                // If we couldn't find a second card to play in one turn, just play the strongest card
                if(strongestPlayableCard != null && bestPlay == null)
                    bestPlay = strongestPlayableCard;
            }
            else{
                // If the last card we played wasn't defensive then we look for the highest health card we can play
                for(Card card : getHand()){ // For each card in our hand
                    if(bestPlay == null){ // If we haven't found a legal card yet
                        if(card.getCost() <= getCurrMana()) // Check if any card has legal mana cost
                            bestPlay = card; // and set it as our best
                    }
                    else{ // Otherwise set it to the highest health card that is within our current mana
                        if(card.getBaseHealth() > bestPlay.getBaseHealth() && card.getCost() <= getCurrMana())
                            bestPlay = card;
                            // In case of a tie in health the higher cost wins.
                        else if(card.getCost() == bestPlay.getCost() && card.getCost() > bestPlay.getCost())
                            bestPlay = card;
                    }
                }
            }
        }
        // (Finally) we determine what type of card it is we are about to play (assuming it's not null)
        if(bestPlay != null) {
            if (bestPlay.getBaseHealth() >= bestPlay.getBaseAttack())
                lastCardTypePlayed = CardType.DEFENSIVE;
            else{
                lastCardTypePlayed = CardType.OFFENSIVE;
            }
        }
        else{
            System.out.println(getName() + " passes their turn.\n");
        }
        return bestPlay;
    }
}
