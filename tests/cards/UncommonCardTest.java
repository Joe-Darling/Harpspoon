package cards;

import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 6/21/2017.
 */
public class UncommonCardTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(5);
        UncommonCard c1 = new UncommonCard();
        System.out.println(c1);
        System.out.println("Card name is Russel - The Conquer? "
                + (c1.getName().equals("Russel - The Conquer") ? "OK":"NO"));
        System.out.println("Card cost is 4? " + (c1.getCost() == 4 ? "OK":"NO"));
        System.out.println("Card attack is 9? " + (c1.getBaseAttack() == 9 ? "OK":"NO"));
        System.out.println("Card health is 3? " + (c1.getCurrHealth() == 3 ? "OK":"NO"));
        System.out.println("Card rarity is Uncommon? " + (c1.getRarity() == Card.Rarity.UNCOMMON ? "OK":"NO"));
        System.out.println("Card powerName is Vigor? " + (c1.getPowerName().equals("Vigor") ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[UV:04:09:03]") ? "OK":"NO"));

        c1 = new UncommonCard();
        System.out.println(c1);
        System.out.println("Card name is Victor - The Victor? "
                + (c1.getName().equals("Victor - The Victor") ? "OK":"NO"));
        System.out.println("Card cost is 4? " + (c1.getCost() == 4 ? "OK":"NO"));
        System.out.println("Card attack is 6? " + (c1.getBaseAttack() == 6 ? "OK":"NO"));
        System.out.println("Card health is 6? " + (c1.getCurrHealth() == 6 ? "OK":"NO"));
        System.out.println("Card rarity is Uncommon? " + (c1.getRarity() == Card.Rarity.UNCOMMON ? "OK":"NO"));
        System.out.println("Card powerName is Lucky? " + (c1.getPowerName().equals("Lucky") ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[UL:04:06:06]") ? "OK":"NO"));

        c1 = new UncommonCard();
        System.out.println(c1);
        System.out.println("Card name is Jenn? "
                + (c1.getName().equals("Jenn") ? "OK":"NO"));
        System.out.println("Card cost is 3? " + (c1.getCost() == 3 ? "OK":"NO"));
        System.out.println("Card attack is 9? " + (c1.getBaseAttack() == 9 ? "OK":"NO"));
        System.out.println("Card health is 3? " + (c1.getCurrHealth() == 3 ? "OK":"NO"));
        System.out.println("Card rarity is Uncommon? " + (c1.getRarity() == Card.Rarity.UNCOMMON ? "OK":"NO"));
        System.out.println("Card powerName is None? " + (c1.getPowerName().equals("None") ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[UN:03:09:03]") ? "OK":"NO"));
    }
}
