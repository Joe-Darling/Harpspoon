package cards;

import cards.Powers.Companion;
import cards.Powers.Mommy;
import cards.Powers.Power;
import game.Harpspoon;

/**
 * Created by Joe on 7/2/2017.
 * Test class for Companion power
 */
public class CompanionPowerTest {
    public static void main(String[] args) {
        Harpspoon.rng.setSeed(2);
        LegendaryCard lc = new LegendaryCard();

        // Testing that it only activates when card state is on Spawn
        System.out.println("Does it return false when card state does not match trigger? " +
                (!lc.getPower().shouldEffectTrigger(lc, Power.CardState.ON_ATTACK) ? "OK" : "NO"));
        System.out.println("Does it return false when card state does not match trigger? " +
                (!lc.getPower().shouldEffectTrigger(lc, Power.CardState.ON_ATTACKED) ? "OK" : "NO"));
        System.out.println("Does it return false when card state does not match trigger? " +
                (!lc.getPower().shouldEffectTrigger(lc, Power.CardState.ON_TARGETED) ? "OK" : "NO"));
        System.out.println("Does it return false when card state does not match trigger? " +
                (!lc.getPower().shouldEffectTrigger(lc, Power.CardState.ON_DEATH) ? "OK" : "NO"));
        System.out.println("Does it return true when card state matches trigger? " +
                (lc.getPower().shouldEffectTrigger(lc, Power.CardState.ON_SPAWN) ? "OK" : "NO"));


        // Makes sure card spawned from effect is correct.
        System.out.println("Ensure output is correct:");
        System.out.println("Triggering effect...");
        Card spawnedCard = lc.getPower().triggerEffect(lc);
        System.out.println(lc);
        System.out.println(spawnedCard);
    }
}
