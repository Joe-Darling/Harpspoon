package cards;

import cards.Powers.Power;
import game.Harpspoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe on 6/23/2017.
 */
public class LegendaryCard extends Card{
    private static final int STAT_DISTRIBUTION = 23;
    private static final int COST = 10;
    private static final List<String> POWERS = new ArrayList<String>(Arrays.asList(
            "Wrath", "Mommy!", "Bubble", "Companion", "Vengeful"));

    public LegendaryCard(){
        super(STAT_DISTRIBUTION,
                COST,
                Rarity.LEGENDARY,
                POWERS.get(Harpspoon.nextInt(0, POWERS.size() - 1)));
    }

    public LegendaryCard(String name, int baseHealth, int baseAttack, Rarity rarity, String powerName, Power power){
        super(name, baseHealth, baseAttack, rarity, powerName, power);
    }
}
