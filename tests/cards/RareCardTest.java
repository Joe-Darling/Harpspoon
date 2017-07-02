package cards;

import cards.Powers.NoPower;
import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 7/2/2017.
 */
public class RareCardTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(0);
        RareCard c1 = new RareCard();
        System.out.println(c1);
        System.out.println("Card name is Chris - The Forgotten? " +
                (c1.getName().equals("Chris - The Forgotten") ? "OK":"NO"));
        System.out.println("Card cost is 6? " + (c1.getCost() == 6 ? "OK":"NO"));
        System.out.println("Card attack is 12? " + (c1.getBaseAttack() == 12 ? "OK":"NO"));
        System.out.println("Card health is 4? " + (c1.getCurrHealth() == 4 ? "OK":"NO"));
        System.out.println("Card rarity is Rare? " + (c1.getRarity() == Card.Rarity.RARE ? "OK":"NO"));
        System.out.println("Card powerName is Angry? " + (c1.getPowerName().equals("Angry") ? "OK":"NO"));
        System.out.println("Card power is not null? " + (c1.getPower() != null ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[RA:06:12:04]") ? "OK":"NO"));

        c1 = new RareCard();
        System.out.println(c1);
        System.out.println("Card name is Alec - The Undying? "
                + (c1.getName().equals("Alec - The Undying") ? "OK":"NO"));
        System.out.println("Card cost is 6? " + (c1.getCost() == 6 ? "OK":"NO"));
        System.out.println("Card attack is 11? " + (c1.getBaseAttack() == 11 ? "OK":"NO"));
        System.out.println("Card health is 5? " + (c1.getCurrHealth() == 5 ? "OK":"NO"));
        System.out.println("Card rarity is Rare? " + (c1.getRarity() == Card.Rarity.RARE ? "OK":"NO"));
        System.out.println("Card powerName is Undying? " + (c1.getPowerName().equals("Undying") ? "OK":"NO"));
        System.out.println("Card power is not null? " + (c1.getPower() != null ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[RU:06:11:05]") ? "OK":"NO"));

        c1 = new RareCard();
        System.out.println(c1);
        System.out.println("Card name is Lenard? " + (c1.getName().equals("Lenard") ? "OK":"NO"));
        System.out.println("Card cost is 6? " + (c1.getCost() == 6 ? "OK":"NO"));
        System.out.println("Card attack is 6? " + (c1.getBaseAttack() == 6 ? "OK":"NO"));
        System.out.println("Card health is 10? " + (c1.getCurrHealth() == 10 ? "OK":"NO"));
        System.out.println("Card rarity is Rare? " + (c1.getRarity() == Card.Rarity.RARE ? "OK":"NO"));
        System.out.println("Card powerName is None? " + (c1.getPowerName().equals("None") ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[RN:06:06:10]") ? "OK":"NO"));

        c1 = new RareCard();
        System.out.println(c1);
        System.out.println("Card name is Anem - The Mobile? "
                + (c1.getName().equals("Anem - The Mobile") ? "OK":"NO"));
        System.out.println("Card cost is 5? " + (c1.getCost() == 5 ? "OK":"NO"));
        System.out.println("Card attack is 9? " + (c1.getBaseAttack() == 9 ? "OK":"NO"));
        System.out.println("Card health is 7? " + (c1.getCurrHealth() == 7 ? "OK":"NO"));
        System.out.println("Card rarity is Rare? " + (c1.getRarity() == Card.Rarity.RARE ? "OK":"NO"));
        System.out.println("Card powerName is Dodge? " + (c1.getPowerName().equals("Dodge") ? "OK":"NO"));
        System.out.println("Card power is not null? " + (c1.getPower() != null ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[RD:05:09:07]") ? "OK":"NO"));


    }
}
