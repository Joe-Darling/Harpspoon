package players;

import cards.*;
import cards.Powers.Power;
import game.Harpspoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Joe on 6/21/2017.
 */
public abstract class Player {
    private static final int CARDS_IN_DECK = 40;

    private String name;
    private final int startingScore = 100;
    private int currScore;
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> cardsInPlay;
    private List<Card> discardPile;
    private int totalMana;
    private int currMana;

    public Player(String name){
        this.name = name;
        newGame();
    }

    public abstract Card playACard();
    public abstract boolean continuePlaying();

    public void loseHealth(int amount){
        System.out.println(getName() + " is attacked directly for " + amount + " damage!");
        currScore -= amount;
        if(currScore < 0)
            currScore = 0;
        System.out.println(name + " has " + currScore + " life remaining.");
    }

    public void newGame(){

        currScore = startingScore;
        totalMana = 0;
        currMana = 0;

        // We create, add cards to, and then randomize the deck.
        deck = new ArrayList<>(CARDS_IN_DECK);

        for(int i = 0; i < CARDS_IN_DECK; i++){ // Remember to change after implementing other card types.
            if(i < 20)
                deck.add(new CommonCard());
            else if(i < 32)
                deck.add(new UncommonCard());
            else if(i < 39)
                deck.add(new RareCard());
            else
                deck.add(new LegendaryCard());
        }
        Collections.shuffle(deck, Harpspoon.rng);

        hand = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            hand.add(deck.remove(deck.size()-1));
        }

        cardsInPlay = new ArrayList<>();
        discardPile = new ArrayList<>();
    }

    public void newRound(){
        if(totalMana < 10)
            totalMana++;
        currMana = totalMana;
        drawCards(1);
    }

    public void drawCards(int n){
        for(int i = 0; i < n; i++){
            hand.add(deck.remove(deck.size()-1));
        }
    }

    public void summonCard(Card card){
        currMana -= card.getCost();
        hand.remove(card);
        cardsInPlay.add(card);
        System.out.println(name + " summons " + card.inGamePrint() + "!");
    }

    public void discardCard(Card card){
        cardsInPlay.remove(card);
        discardPile.add(card);
        System.out.println(card.getName() + " has fallen!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCurrScore() {
        return currScore;
    }

    public void setCurrScore(int currScore) {
        this.currScore = currScore;
    }

    public List<Card> getDeck(){
        return deck;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getCardsInPlay() {
        return cardsInPlay;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public int getTotalMana(){
        return totalMana;
    }

    public int getCurrMana() {
        return currMana;
    }
}
