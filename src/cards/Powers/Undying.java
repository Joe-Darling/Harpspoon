package cards.Powers;

import cards.Card;

/**
 * Created by Joe on 6/22/2017.
 */
public class Undying extends Power{

    private boolean usedEffect;

    public Undying(){
        usedEffect = false;
    }

    public boolean shouldEffectTrigger(Card card, CardState state) {
        return state == CardState.ON_ATTACKED && card.getCurrHealth() == 0 && !usedEffect;
    }

    public void triggerEffect(Card card) {
        card.gainCurrHealth(1);
        System.out.println(card.getName() + " is undying! He survives the attack with 1 hp.");
        usedEffect = true;
    }
}
