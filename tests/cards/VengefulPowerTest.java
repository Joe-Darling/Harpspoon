package cards;

import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 7/2/2017.
 * Test class for Vengeful power
 */
public class VengefulPowerTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(3);
        LegendaryCard c1 = new LegendaryCard();
        System.out.println(c1);
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_TARGETED) ? "OK":"NO"));

        c1.sustainDamage(6);
        System.out.println("Does trigger return true when valid? " +
                (c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACKED) ? "OK":"NO"));

        System.out.println("Triggering effect...");
        c1.getPower().triggerEffect(c1);
        System.out.println("Card bonus attack is 3? " + (c1.getBonusAttack() == 3 ? "OK":"NO"));
    }
}
