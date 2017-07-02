package cards;

import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 7/2/2017.
 * Test class for undying power
 */
public class UndyingPowerTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(3);
        RareCard c1 = new RareCard();
        System.out.println(c1);
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACKED) ? "OK":"NO"));

        c1.sustainDamage(1);
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACKED) ? "OK":"NO"));

        c1.sustainDamage(3);
        System.out.println("Does trigger return true when valid? " +
                (c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACKED) ? "OK":"NO"));

        System.out.println("Triggering effect...");
        c1.getPower().triggerEffect(c1);
        System.out.println("Card health is 1? " + (c1.getCurrHealth() == 1 ? "OK":"NO"));
        System.out.println("Does trigger return false after use? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACK) ? "OK":"NO"));
    }
}
