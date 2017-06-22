package cards.Powers;

import cards.Card;
import game.MainGame;

/**
 * Created by Joe on 6/22/2017.
 */
public class Dodge extends Power {

    public boolean shouldEffectTrigger(Card card, CardState state) {
        return state == CardState.ON_TARGETED && MainGame.nextInt(1,100) > 15;
    }

    public void triggerEffect(Card card) {
        card.setInvincible(true);
        System.out.println(card.getName() + " Dodges the damage and takes no damage!");
    }
}
