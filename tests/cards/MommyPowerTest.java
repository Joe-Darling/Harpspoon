package cards;

import cards.Powers.Mommy;
import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 6/24/2017.
 */
public class MommyPowerTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(6);
        LegendaryCard lc = new LegendaryCard();
        lc.setPower(new Mommy());

        // Testing that it only activates when card state is on death
        System.out.println("Does it return false when card state does not match trigger? " +
                (!lc.getPower().shouldEffectTrigger(lc, Power.CardState.ON_ATTACK) ? "OK" : "NO"));
        System.out.println("Does it return false when card state does not match trigger? " +
                (!lc.getPower().shouldEffectTrigger(lc, Power.CardState.ON_ATTACKED) ? "OK" : "NO"));
        System.out.println("Does it return false when card state does not match trigger? " +
                (!lc.getPower().shouldEffectTrigger(lc, Power.CardState.ON_TARGETED) ? "OK" : "NO"));
        System.out.println("Does it return false when card state does not match trigger? " +
                (!lc.getPower().shouldEffectTrigger(lc, Power.CardState.ON_SPAWN) ? "OK" : "NO"));
        System.out.println("Does it return true when card state matches trigger? " +
                (lc.getPower().shouldEffectTrigger(lc, Power.CardState.ON_DEATH) ? "OK" : "NO"));

        // Makes sure card spawned from effect is correct.
        System.out.println("Ensure output is correct");
        Card spawnedCard = lc.getPower().triggerEffect(lc);
        System.out.println(lc);
        System.out.println(spawnedCard);
    }
}
