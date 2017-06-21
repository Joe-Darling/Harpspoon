package cards.Powers;

import cards.Card;

/**
 * Created by Joe on 6/21/2017.
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
    public abstract void triggerEffect(Card card);
}
