package cards;

import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 7/2/2017.
 * Class for testing dodge power
 */
public class DodgePowerTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(6);
        RareCard c1 = new RareCard();
        System.out.println(c1);
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACKED) ? "OK":"NO"));
        // How to output many times until the 15% chance hit
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_TARGETED) ? "OK":"NO"));
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_TARGETED) ? "OK":"NO"));
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_TARGETED) ? "OK":"NO"));
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_TARGETED) ? "OK":"NO"));
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_TARGETED) ? "OK":"NO"));
        System.out.println("Does trigger return true when valid? " +
                (c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_TARGETED) ? "OK":"NO"));

        System.out.println("Triggering effect...");
        c1.getPower().triggerEffect(c1);
        System.out.println("Card is invincible? " + (c1.isInvincible() ? "OK":"NO"));
        System.out.println("Does trigger return false after use? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACK) ? "OK":"NO"));
    }
}
