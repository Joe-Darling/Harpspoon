package cards.Powers;

import cards.Card;

/**
 * Created by Joe on 6/22/2017.
 */
public class Angry extends Power {

    private boolean usedEffect;
    private int lastCurrHealth;

    public Angry(int health){
        usedEffect = false;
        lastCurrHealth = health;
    }

    public boolean shouldEffectTrigger(Card card, CardState state) {
        int damageTaken = lastCurrHealth - card.getCurrHealth();
        lastCurrHealth = card.getCurrHealth();
        return state == CardState.ON_ATTACKED && !usedEffect && damageTaken >= (card.getBaseHealth() / 2) &&
                card.getCurrHealth() != 0;
    }

    public Card triggerEffect(Card card) {
        System.out.println(card.getName() + " is angry! Attack increased by 2.");
        card.setBaseAttack(card.getBaseAttack() + 2);
        return null;
    }
}
