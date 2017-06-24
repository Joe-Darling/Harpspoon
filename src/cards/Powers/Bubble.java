package cards.Powers;

import cards.Card;

/**
 * Created by Joe on 6/23/2017.
 */
public class Bubble extends Power{

    private boolean usedEffect;

    public Bubble(){
        usedEffect = false;
    }

    public boolean shouldEffectTrigger(Card card, CardState state) {
        return !usedEffect && state == CardState.ON_TARGETED;
    }

    public Card triggerEffect(Card card) {
        card.setInvincible(true);
        System.out.println(card.getName() + "'s bubble shield protected him from the attack!");
        usedEffect = true;
        return null;
    }
}
