package cards.Powers;

import cards.Card;
import game.Harpspoon;

/**
 * Created by Joe on 6/21/2017.
 */
public class Lucky extends Power {

    // The card is allowed to use it's effect if it rolls over 50 and it has been targeted for an attack.
    public boolean shouldEffectTrigger(Card card, CardState state) {
        return state == CardState.ON_TARGETED && Harpspoon.nextInt(1,100) > 50;
    }

    // The effect gives a temporary defense boost of one.
    public Card triggerEffect(Card card) {
        card.setBonusResistance(card.getBonusResistance() + 1);
        System.out.println(card.getName() + " is Lucky! Damaged decreased by 1 this turn.");
        return null;
    }
}
