package cards;

import cards.Powers.Power;
import game.MainGame;

/**
 * Created by Joe on 6/21/2017.
 */
public class UncommonCardTest {
    public static void main(String[] args) {
        MainGame.rng.setSeed(1);
        UncommonCard c1 = new UncommonCard();
        System.out.println(c1);
        System.out.println("Card name is Russel - The Conquer? " + (c1.getName().equals("Russel - The Conquer") ? "OK":"NO"));
        System.out.println("Card cost is 4? " + (c1.getCost() == 4 ? "OK":"NO"));
        System.out.println("Card attack is 4? " + (c1.getBaseAttack() == 4 ? "OK":"NO"));
        System.out.println("Card health is 8? " + (c1.getCurrHealth() == 8 ? "OK":"NO"));
        System.out.println("Card rarity is Uncommon? " + (c1.getRarity() == Rarity.UNCOMMON ? "OK":"NO"));
        System.out.println("Card powerName is Vigor? " + (c1.getPowerName().equals("Vigor") ? "OK":"NO"));
        System.out.println("Card power is not null? " + (c1.getPower() != null ? "OK":"NO"));
        System.out.println("Card Abbrev correct? " + (c1.getAbbrevRep().equals("UV:04:04:08") ? "OK":"NO"));
        int remainingDamage = c1.sustainDamage(4);
        System.out.println("Card health is 4? " + (c1.getCurrHealth() == 4 ? "OK":"NO"));
        System.out.println("Damage returned == 0? " + (remainingDamage == 0 ? "OK":"NO"));
        remainingDamage = c1.sustainDamage(5);
        System.out.println("Card health is 0? " + (c1.getCurrHealth() == 0 ? "OK":"NO"));
        System.out.println("Damage returned == 1? " + (remainingDamage == 1 ? "OK":"NO"));
        System.out.println("Does effect trigger when not supposed to? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACKED) ? "OK":"NO"));
        System.out.println("Does effect trigger when supposed to? " +
                (c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACK) ? "OK":"NO"));
        c1.getPower().triggerEffect(c1);
        System.out.println("Card Bonus attack is 1? " + (c1.getBonusAttack() == 1 ? "OK":"NO"));
        System.out.println("Does effect trigger when not supposed to? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACK) ? "OK":"NO"));
    }
}
