package players;

import cards.Card;
import game.Harpspoon;

/**
 * Created by Joe on 7/5/2017.
 * Cleric test class
 */
public class ClericTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(1);
        Player p1 = new Cleric(2);
        p1.newGame();
        p1.newRound();
        p1.drawCards(4);
        for(Card card : p1.getHand())
            System.out.println(card);
        Card correctCard;
        Card c = p1.playACard();
        System.out.println("Did Cleric pick right card? " + (c == null ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for(Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(1);
        c = p1.playACard();
        System.out.println("Did Cleric pick right card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for(Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(1);
        c = p1.playACard();
        p1.summonCard(c);
        System.out.println("Did Cleric pick right card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for(Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(3);
        c = p1.playACard();
        System.out.println("Did Cleric pick right card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");
    }
}
