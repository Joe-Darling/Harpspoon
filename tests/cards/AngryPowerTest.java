package cards;

import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 7/2/2017.
 * Test class for angry ability
 */
public class AngryPowerTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(0);
        RareCard c1 = new RareCard();
        System.out.println(c1);
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACKED) ? "OK":"NO"));

        c1.sustainDamage(1);
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACKED) ? "OK":"NO"));

        c1.sustainDamage(2);
        System.out.println("Does trigger return true when valid? " +
                (c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACKED) ? "OK":"NO"));

        System.out.println("Triggering effect...");
        int previousAttack = c1.getBaseAttack();
        c1.getPower().triggerEffect(c1);
        System.out.println("Card base attack is + 2? " + (c1.getBaseAttack() ==  previousAttack + 2 ? "OK":"NO"));
        System.out.println("Does trigger return false after use? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACK) ? "OK":"NO"));
    }
}
