package cards.Powers;

import cards.Card;

/**
 * Created by Joe on 6/21/2017.
 * This class is used as a blueprint for all of the powers in the game
 * The enum CardState is used to see if the current state of the card allows for it's ability to be used.
 * For example, a card with Vigor would be allowed to use it's power when the shouldEffectTrigger method is
 * called during the battle phase and the attack is about to begin.
 */

public abstract class Power {


    public enum CardState {
        TURN_START,
        ON_SPAWN,
        ON_DEATH,
        ON_ATTACK,
        ON_TARGETED,
        ON_ATTACKED,
    }

    public abstract boolean shouldEffectTrigger(Card card, CardState state);
    public abstract Card triggerEffect(Card card);
}
