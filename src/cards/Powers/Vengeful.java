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
        return state == CardState.ON_ATTACKED && damageTaken > 0 && card.getCurrHealth() > 0;
    }

    public Card triggerEffect(Card card) {
        System.out.println(card.getName() + " is Vengeful! Deals " + (damageTaken / 2) + " extra damage next turn.");
        card.setBonusAttack(card.getBonusAttack() + (damageTaken / 2));
        return null;
    }
}
