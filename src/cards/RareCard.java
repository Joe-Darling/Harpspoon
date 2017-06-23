package cards;

import game.Harpspoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe on 6/22/2017.
 */
public class RareCard extends Card {
    private static final int STAT_DISTRIBUTION = 15;
    private static final int MIN_COST = 4;
    private static final int MAX_COST = 7;
    private static final List<String> POWERS = new ArrayList<String>(Arrays.asList(
            "None", "Dodge", "Undying", "Angry"));

    public RareCard(){
        super(STAT_DISTRIBUTION,
                Harpspoon.nextInt(MIN_COST, MAX_COST),
                Rarity.RARE,
                POWERS.get(Harpspoon.nextInt(0, POWERS.size() - 1)));
    }
}
