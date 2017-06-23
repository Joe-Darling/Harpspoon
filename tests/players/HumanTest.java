package players;

import cards.Card;
import game.Harpspoon;

/**
 * Created by Joe on 6/23/2017.
 */
public class HumanTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(3);
        Player p1 = new Human();

        System.out.println("You entered '" + p1.getName() + "', right?");

        System.out.println("\nTry picking an invalid card.\n");
        Card c = p1.playACard();
        System.out.println("\nWas the card null? Card was: " + c);

        p1.newRound();
        System.out.println("\nTry passing (Entering 0).\n");
        c = p1.playACard();
        System.out.println("Was the card null? Card was: " + c);

        p1.newRound();
        System.out.println("\nTry entering number outside of legal range.\n");
        c = p1.playACard();
        System.out.println("Was the card null? Card was: " + c);

        p1.newRound();
        System.out.println("\nTry picking a valid card.\n");
        c = p1.playACard();
        System.out.println("Card picked: " + c);
        System.out.println("Ensure card is the same card picked.\n");

        boolean continuePlaying = p1.continuePlaying();
        if(continuePlaying)
            System.out.println("You wanted to continue playing right?");
        else
            System.out.println("You wanted to stop playing right?");

        continuePlaying = p1.continuePlaying();
        if(continuePlaying)
            System.out.println("You wanted to continue playing right?");
        else
            System.out.println("You wanted to stop playing right?");

    }

}
