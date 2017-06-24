package cards.Powers;

import cards.Card;
import cards.LegendaryCard;

/**
 * Created by Joe on 6/23/2017.
 */
public class Mommy extends Power{

    public boolean shouldEffectTrigger(Card card, CardState state) {
        return state == CardState.ON_DEATH;
    }

    public Card triggerEffect(Card card) {
        String name = card.getName() + "' Mom";
        int baseHealth = card.getBaseHealth() + 5;
        int baseAttack = card.getBaseAttack() - 5;
        if(baseAttack < 0)
            baseAttack = 0;
        System.out.println("As " + card.getName() + " falls, his mom, " + name + "rises from the ashes!");
        return new LegendaryCard(name, baseHealth, baseAttack, Card.Rarity.LEGENDARY, "Wrath", new Wrath());
    }
}
