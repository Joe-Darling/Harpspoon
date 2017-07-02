package cards;

import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 7/2/2017.
 * Test class for Vigor ability
 */
public class VigorPowerTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(1);
        UncommonCard c1 = new UncommonCard();
        System.out.println(c1);
        System.out.println("Does trigger return false when invalid? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACKED) ? "OK":"NO"));
        System.out.println("Does trigger return true when valid? " +
                (c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACK) ? "OK":"NO"));
        System.out.println("Triggering effect...");
        c1.getPower().triggerEffect(c1);
        System.out.println("Card Bonus attack is 1? " + (c1.getBonusAttack() == 1 ? "OK":"NO"));
        System.out.println("Does trigger return false after use? " +
                (!c1.getPower().shouldEffectTrigger(c1, Power.CardState.ON_ATTACK) ? "OK":"NO"));
    }
}
