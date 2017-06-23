package cards.Powers;

import cards.Card;

/**
 * Created by Joe on 6/23/2017.
 */
public class Wrath extends Power{

    public boolean shouldEffectTrigger(Card card, CardState state) {
        return state == CardState.TURN_START;
    }

    public void triggerEffect(Card card) {
        card.setBaseAttack(card.getBaseAttack() + 1);
        System.out.println(card.getName() + " has wrath! Damage increased by 1.");
    }
}
