package players;

import cards.Card;
import game.MainGame;

/**
 * Created by Joe on 6/21/2017.
 * Test Class for the Mage Player
 */
public class MageTest {
    public static void main(String[] args) {
        MainGame.rng.setSeed(2);
        Player p1 = new Mage(2);
        p1.newRound();
        for(Card card : p1.getHand())
            System.out.println(card);
        Card correctCard;
        Card c = p1.playACard();
        System.out.println("Did Mage pick right card? " + (c == null ? "OK" : "NO") + "\n");

        p1.newRound();
        for(Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(0);
        c = p1.playACard();
        System.out.println("Did Mage pick right card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        for(Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(3);
        c = p1.playACard();
        p1.summonCard(c);
        System.out.println("Did Mage pick right card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        for(Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(3);
        c = p1.playACard();
        System.out.println("Did Mage pick right card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");
    }
}
