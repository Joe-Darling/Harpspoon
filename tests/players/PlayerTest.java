package players;

import cards.Card;
import game.Harpspoon;

/**
 * Created by Joe on 6/21/2017.
 * Test file for player class, since it is abstract we are using the mage extended
 * class to create objects.
 */
public class PlayerTest {

    public static void main(String[] args) {
        Harpspoon.rng.setSeed(2);
        Player p1 = new Mage(2);
        p1.newGame();
        p1.drawCards(4);
        System.out.println("Initialization test");
        System.out.println("Deck had 36 cards? " + (p1.getDeck().size() == 36 ? "OK":"NO"));
        System.out.println("Hand has 4 cards? " + (p1.getHand().size() == 4 ? "OK":"NO"));
        System.out.println("0 cards in play? " + (p1.getCardsInPlay().size() == 0 ? "OK":"NO"));
        System.out.println("Discard has 0 cards? " + (p1.getDiscardPile().size() == 0 ? "OK":"NO"));
        System.out.println("Score is 100? " + (p1.getCurrScore() == 100 ? "OK":"NO"));
        System.out.println("0/0 Mana? " + (p1.getCurrMana() == 0 && p1.getTotalMana() == 0 ? "OK":"NO"));

        System.out.println("\nTesting Turn");
        p1.newRound();
        p1.drawCards(1);
        System.out.println("Deck had 35 cards? " + (p1.getDeck().size() == 35 ? "OK":"NO"));
        System.out.println("1/1 Mana? " + (p1.getCurrMana() == 1 && p1.getTotalMana() == 1 ? "OK":"NO"));
        System.out.println("Hand has 5 cards? " + (p1.getHand().size() == 5 ? "OK":"NO"));

        System.out.println("\nTesting Playing a Card");
        Card card = p1.playACard();
        System.out.println(card);
        p1.summonCard(card);
        System.out.println("0/1 Mana? " + (p1.getCurrMana() == 0 && p1.getTotalMana() == 1 ? "OK":"NO"));
        System.out.println("Hand has 4 cards? " + (p1.getHand().size() == 4 ? "OK":"NO"));
        System.out.println("1 card in play? " + (p1.getCardsInPlay().size() == 1 ? "OK":"NO"));

        System.out.println("\nTesting Destroying a Card");
        p1.discardCard(card);
        System.out.println("0 cards in play? " + (p1.getCardsInPlay().size() == 0 ? "OK":"NO"));
        System.out.println("Discard has 1 card? " + (p1.getDiscardPile().size() == 1 ? "OK":"NO"));

        // All of the AI classes use this so I'm just testing it here instead of doing it multiple times.
        System.out.println("\nTesting Playing again");
        System.out.println("Play again method correct output? " + (p1.continuePlaying() ? "OK":"NO"));
        System.out.println("Play again method correct output? " + (!p1.continuePlaying() ? "OK":"NO"));
        System.out.println("Play again method correct output? " + (p1.continuePlaying() ? "OK":"NO"));
    }
}
