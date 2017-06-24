package cards.Powers;

import cards.Card;
import cards.LegendaryCard;

/**
 * Created by Joe on 6/23/2017.
 */
public class Companion extends Power{

    public boolean shouldEffectTrigger(Card card, CardState state) {
        return state == CardState.ON_SPAWN;
    }

    public Card triggerEffect(Card card) {
        String name = "Buttercup"; // Note I purposefully took off the exclamation point for formatting reasons
        int baseHealth = 24;
        int baseAttack = 0;
        System.out.println(card.getName() + " didn't come alone. She brought her companion Buttercup!");
        return new LegendaryCard(name, baseHealth, baseAttack, Card.Rarity.LEGENDARY, "Bubble", new Bubble());
    }
}
