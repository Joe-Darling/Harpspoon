package cards.Powers;

import cards.Card;

/**
 * Created by Joe on 6/21/2017.
 */
public class Vigor extends Power {

    private boolean usedEffect;

    public Vigor(){
        usedEffect = false;
    }

    public boolean shouldEffectTrigger(Card card, CardState state) {
        return !usedEffect && state == CardState.ON_ATTACK;
    }

    public void triggerEffect(Card card) {
        card.setBonusAttack(card.getBonusAttack() + 1);
        System.out.println(card.getName() + " has Vigor! Damaged increased by 1 this turn.");
    }
}
