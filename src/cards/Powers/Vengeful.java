package cards.Powers;

import cards.Card;

/**
 * Created by Joe on 6/23/2017.
 */
public class Vengeful extends Power {

    private int lastCurrHealth;
    private int damageTaken = 0;

    public Vengeful(int health){
        lastCurrHealth = health;
        damageTaken = 0;
    }

    public boolean shouldEffectTrigger(Card card, CardState state) {
        damageTaken = lastCurrHealth - card.getCurrHealth();
        lastCurrHealth = card.getCurrHealth();
        return state == CardState.ON_ATTACKED && damageTaken > 0;
    }

    public void triggerEffect(Card card) {
        card.setBonusAttack(card.getBonusAttack() + (damageTaken / 2));
    }
}
