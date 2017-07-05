package players;

import cards.Card;
import game.Harpspoon;

/**
 * Created by Joe on 7/1/2017.
 */
public class DragonTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(1);
        Player p1 = new Dragon(2);
        p1.newGame();
        p1.newRound();
        p1.drawCards(4);
        for (Card card : p1.getHand())
            System.out.println(card);
        Card correctCard;
        Card c = p1.playACard();
        System.out.println("Did Dragon pass? " + (c == null ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for (Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(1);
        c = p1.playACard();
        p1.summonCard(c);
        System.out.println("Did Dragon pick highest HP legal card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for (Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(2);
        c = p1.playACard();
        p1.summonCard(c);
        System.out.println("Did Dragon pick highest ATT legal card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for (Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(2);
        c = p1.playACard();
        p1.summonCard(c);
        System.out.println("Did Dragon pick highest ATT legal card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for (Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(0);
        c = p1.playACard();
        p1.summonCard(c);
        System.out.println("Did Dragon pick highest HP legal card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for (Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(3);
        c = p1.playACard();
        p1.summonCard(c);
        System.out.println("Did Dragon Summon stored Card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for (Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(3);
        c = p1.playACard();
        p1.summonCard(c);
        System.out.println("Did Dragon summon highest HP legal? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for (Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(2);
        c = p1.playACard();
        p1.summonCard(c);
        System.out.println("Did Dragon Summon stored Card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for (Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(3);
        c = p1.playACard();
        p1.summonCard(c);
        System.out.println("Did Dragon pick highest HP legal card? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");

        p1.newRound();
        p1.drawCards(1);
        for (Card card : p1.getHand())
            System.out.println(card);
        correctCard = p1.getHand().get(1);
        c = p1.playACard();
        p1.summonCard(c);
        System.out.println("Did Dragon pick 10 mana card on 10 mana? " + (correctCard.equals(c) ? "OK" : "NO") + "\n");
    }
}
