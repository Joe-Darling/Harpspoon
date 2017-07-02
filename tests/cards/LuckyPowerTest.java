package cards;

import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 7/2/2017.
 * Test class for lucky power
 */
public class LuckyPowerTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(2);
        UncommonCard c1 = new UncommonCard();
        System.out.println(c1);
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACK) ? "OK":"NO"));
        System.out.println("Does trigger return true when valid? " +
                (c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_TARGETED) ? "OK":"NO"));
        System.out.println("Triggering effect...");
        c1.getPower().triggerEffect(c1);
        System.out.println("Card Bonus resistance is 1? " + (c1.getBonusResistance() == 1 ? "OK":"NO"));
    }
}
