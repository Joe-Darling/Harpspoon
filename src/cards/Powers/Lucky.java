package cards.Powers;

import cards.Card;
import game.MainGame;

/**
 * Created by Joe on 6/21/2017.
 */
public class Lucky extends Power {

    public boolean shouldEffectTrigger(Card card, CardState state) {
        return state == CardState.ON_TARGETED && MainGame.nextInt(1,100) <= 50;
    }

    public void triggerEffect(Card card) {
        card.setBonusResistance(card.getBonusResistance() + 1);
        System.out.println(card.getName() + " is Lucky! Damaged decreased by 1 this turn.");
    }
}
