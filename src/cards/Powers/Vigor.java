package cards.Powers;

import cards.Card;

/**
 * Created by Joe on 6/21/2017.
 */
public class Vigor extends Power {

    // Whether or not the card has used it's effect yet this battle
    private boolean usedEffect;

    public Vigor(){
        usedEffect = false;
    }

    // This card is allowed to use it's effect if it hasn't yet and if it's during the attack phase.
    public boolean shouldEffectTrigger(Card card, CardState state) {
        return !usedEffect && state == CardState.ON_ATTACK;
    }

    // The effect gives a temporary attack boost of one.
    public Card triggerEffect(Card card) {
        card.setBonusAttack(card.getBonusAttack() + 1);
        System.out.println(card.getName() + " has Vigor! Damaged increased by 1 this turn.");
        usedEffect = true;
        return null;
    }
}
