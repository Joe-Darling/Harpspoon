package cards;

import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 6/21/2017.
 */
public class CommonCardTest {

    // This method tests the CommonCard class and the card class.
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(0);
        CommonCard c1 = new CommonCard();
        System.out.println(c1);
        System.out.println("Card name is Victoria? " + (c1.getName().equals("Victoria") ? "OK":"NO"));
        System.out.println("Card cost is 1? " + (c1.getCost() == 1 ? "OK":"NO"));
        System.out.println("Card attack is 1? " + (c1.getBaseAttack() == 1 ? "OK":"NO"));
        System.out.println("Card health is 7? " + (c1.getCurrHealth() == 7 ? "OK":"NO"));
        System.out.println("Card rarity is common? " + (c1.getRarity() == Card.Rarity.COMMON ? "OK":"NO"));
        System.out.println("Card powerName is None? " + (c1.getPowerName().equals("None") ? "OK":"NO"));
        System.out.println("Effect trigger false?? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_DEATH) ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("[CN:01:01:07]") ? "OK":"NO"));
        int remainingDamage = c1.sustainDamage(3);
        System.out.println("Card health is 4? " + (c1.getCurrHealth() == 4 ? "OK":"NO"));
        System.out.println("Damage returned == 0? " + (remainingDamage == 0 ? "OK":"NO"));
        remainingDamage = c1.sustainDamage(5);
        System.out.println("Card health is 0? " + (c1.getCurrHealth() == 0 ? "OK":"NO"));
        System.out.println("Damage returned == 1? " + (remainingDamage == 1 ? "OK":"NO"));
    }
}
