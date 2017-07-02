package cards;

import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 7/2/2017.
 * Test class for Wrath ability
 */
public class WrathPowerTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(1);
        LegendaryCard c1 = new LegendaryCard();
        System.out.println(c1);
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACKED) ? "OK":"NO"));
        System.out.println("Does trigger return true when valid? " +
                (c1.getPower().shouldEffectTrigger(c1, Power.CardState.TURN_START) ? "OK":"NO"));
        System.out.println("Triggering effect...");
        int previousAttack = c1.getBaseAttack();
        c1.getPower().triggerEffect(c1);
        System.out.println("Card base attack is increased by 1? " +
                (c1.getBaseAttack() == previousAttack + 1 ? "OK":"NO"));
        c1.getPower().triggerEffect(c1);
        System.out.println("Card base attack is increased by 2? " +
                (c1.getBaseAttack() == previousAttack + 2 ? "OK":"NO"));
    }
}
