package cards;

import game.Harpspoon;

/**
 * Created by Joe on 7/2/2017.
 */
public class LegendaryCardTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(2);
        LegendaryCard c1 = new LegendaryCard();
        System.out.println(c1);
        System.out.println("Card name is Cassandra - The Protector? " +
                (c1.getName().equals("Cassandra - The Protector") ? "OK":"NO"));
        System.out.println("Card cost is 10? " + (c1.getCost() == 10 ? "OK":"NO"));
        System.out.println("Card attack is 11? " + (c1.getBaseAttack() == 11 ? "OK":"NO"));
        System.out.println("Card health is 13? " + (c1.getCurrHealth() == 13 ? "OK":"NO"));
        System.out.println("Card rarity is Legendary? " + (c1.getRarity() == Card.Rarity.LEGENDARY ? "OK":"NO"));
        System.out.println("Card powerName is Companion? " + (c1.getPowerName().equals("Companion") ? "OK":"NO"));
        System.out.println("Card power is not null? " + (c1.getPower() != null ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[LC:10:11:13]") ? "OK":"NO"));

        c1 = new LegendaryCard();
        System.out.println(c1);
        System.out.println("Card name is Logan - The Unhinged? " +
                (c1.getName().equals("Logan - The Unhinged") ? "OK":"NO"));
        System.out.println("Card cost is 10? " + (c1.getCost() == 10 ? "OK":"NO"));
        System.out.println("Card attack is 4? " + (c1.getBaseAttack() == 4 ? "OK":"NO"));
        System.out.println("Card health is 20? " + (c1.getCurrHealth() == 20 ? "OK":"NO"));
        System.out.println("Card rarity is Legendary? " + (c1.getRarity() == Card.Rarity.LEGENDARY ? "OK":"NO"));
        System.out.println("Card powerName is Wrath? " + (c1.getPowerName().equals("Wrath") ? "OK":"NO"));
        System.out.println("Card power is not null? " + (c1.getPower() != null ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[LW:10:04:20]") ? "OK":"NO"));

        c1 = new LegendaryCard();
        System.out.println(c1);
        System.out.println("Card name is Karis - The Forsaken? " +
                (c1.getName().equals("Karis - The Forsaken") ? "OK":"NO"));
        System.out.println("Card cost is 10? " + (c1.getCost() == 10 ? "OK":"NO"));
        System.out.println("Card attack is 17? " + (c1.getBaseAttack() == 17 ? "OK":"NO"));
        System.out.println("Card health is 7? " + (c1.getCurrHealth() == 7 ? "OK":"NO"));
        System.out.println("Card rarity is Legendary? " + (c1.getRarity() == Card.Rarity.LEGENDARY ? "OK":"NO"));
        System.out.println("Card powerName is Vengeful? " + (c1.getPowerName().equals("Vengeful") ? "OK":"NO"));
        System.out.println("Card power is not null? " + (c1.getPower() != null ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[LV:10:17:07]") ? "OK":"NO"));

        c1 = new LegendaryCard();
        System.out.println(c1);
        System.out.println("Card name is Joe - The Destroyer of Worlds? " +
                (c1.getName().equals("Joe - The Destroyer of Worlds") ? "OK":"NO"));
        System.out.println("Card cost is 10? " + (c1.getCost() == 10 ? "OK":"NO"));
        System.out.println("Card attack is 8? " + (c1.getBaseAttack() == 8 ? "OK":"NO"));
        System.out.println("Card health is 16? " + (c1.getCurrHealth() == 16 ? "OK":"NO"));
        System.out.println("Card rarity is Legendary? " + (c1.getRarity() == Card.Rarity.LEGENDARY ? "OK":"NO"));
        System.out.println("Card powerName is Mommy? " + (c1.getPowerName().equals("Mommy") ? "OK":"NO"));
        System.out.println("Card power is not null? " + (c1.getPower() != null ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[LM:10:08:16]") ? "OK":"NO"));
    }
}
