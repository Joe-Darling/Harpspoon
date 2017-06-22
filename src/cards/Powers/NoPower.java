package cards.Powers;

import cards.Card;

/**
 * Created by Joe on 6/22/2017.
 *
 * Class for cards with no power.
 * I created this class so that I wouldn't have to compare to null for cards without powers.
 */
public class NoPower extends Power {

    public boolean shouldEffectTrigger(Card card, CardState state) {
        return false;
    }

    public void triggerEffect(Card card) {

    }
}
