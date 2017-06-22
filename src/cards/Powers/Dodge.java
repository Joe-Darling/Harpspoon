package cards.Powers;

import cards.Card;
import game.MainGame;

/**
 * Created by Joe on 6/22/2017.
 */
public class Dodge extends Power {

    public boolean shouldEffectTrigger(Card card, CardState state) {
        return state == CardState.ON_TARGETED && MainGame.nextInt(1,100) > 85;
    }

    public void triggerEffect(Card card) {
        card.setInvincible(true);
        System.out.println(card.getName() + " ninja dodges the attack and takes no damage!");
    }
}
