package cards.Powers;

import cards.Card;
import game.Harpspoon;

/**
 * Created by Joe on 6/22/2017.
 * Dodge power class
 */
public class Dodge extends Power {

    public boolean shouldEffectTrigger(Card card, CardState state) {
        return state == CardState.ON_TARGETED && Harpspoon.nextInt(1,100) > 85;
    }

    public Card triggerEffect(Card card) {
        card.makeInvincible();
        System.out.println(card.getName() + " dodges the attack!");
        return null;
    }
}
