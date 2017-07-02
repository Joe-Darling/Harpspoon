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
    private final int STARTING_SCORE = 100;

    private String name;
    private int currScore;
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> cardsInPlay;
    private List<Card> discardPile;
    private int totalMana;
    private int currMana;

    public Player(String name){
        currScore = STARTING_SCORE;
        this.name = name;
    }

    public abstract Card playACard();
    public abstract boolean continuePlaying();

    public void loseHealth(int amount){
        currScore -= amount;
        if(currScore < 0)
            currScore = 0;

        System.out.println(getName() + " is attacked directly for " + amount + " damage!");
        System.out.println(name + " has " + currScore + " life remaining.");

        Harpspoon.reporter.addStringToReport("PLAYER DAMAGED:" + name + ":damage dealt=" + amount +
                ":remaining score=" + currScore);
    }

    public void newGame(){
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

        Harpspoon.reporter.addStringToReport("PLAYER DECK CREATED");
        Harpspoon.reporter.addStringToReport(dispDeck());

        hand = new ArrayList<>();
        cardsInPlay = new ArrayList<>();
        discardPile = new ArrayList<>();
    }

    public void newRound(){
        if(totalMana < 10)
            totalMana++;
        currMana = totalMana;
    }

    public void drawCards(int n){
        for(int i = 0; i < n; i++){
            Card card = deck.remove(deck.size()-1);
            Harpspoon.reporter.addStringToReport("CARD DRAWN:" + name + ":" + card.getAbbrevRep());
            hand.add(card);
        }
    }

    public void summonCard(Card card){
        currMana -= card.getCost();
        hand.remove(card);
        cardsInPlay.add(card);
        System.out.println(name + " summons " + card.inGamePrint() + "!");
        Harpspoon.reporter.addStringToReport("CARD PLAYED:" + name + ":" + card.getAbbrevRep() +
                ":remaining resource points=" + currMana);
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

    public String playerPrint(int score){
        return "  Player{name=" + name + ",total resource points=" + totalMana + ",starting score=" + STARTING_SCORE +
                ",current score=" + currScore + ",total score=" + score + "}";
    }

    public String dispDeck(){
        String output = "  " + name;
        for(int i = 0; i < deck.size(); i++){
            if(i % 5 == 0)
                output += "\n  ";
            output += deck.get(i).getAbbrevRep();
        }
        return output;
    }

    public String dispHand(){
        String output = "CARDS IN HAND:" + name;

        for(int i = 0; i < hand.size(); i++){
            if(i % 5 == 0)
                output += "\n  ";
            output += hand.get(i).getAbbrevRep();
        }
        return output;
    }

    public String dispCardsInPlay(){
        String output = "CARDS IN PLAY:" + name;
        if(cardsInPlay.size() == 0)
            output += "\n--NO CARDS IN PLAY--";
        for(int i = 0; i < cardsInPlay.size(); i++){
            if(i % 5 == 0)
                output += "\n  ";
            output += cardsInPlay.get(i).getAbbrevRep();
        }
        return output;
    }
}
