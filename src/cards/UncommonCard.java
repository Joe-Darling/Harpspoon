package cards;

import game.Harpspoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe on 6/21/2017.
 *
 */
public class UncommonCard extends Card{
    private static final int STAT_DISTRIBUTION = 11;
    private static final int MIN_COST = 2;
    private static final int MAX_COST = 5;
    private static final List<String> POWERS = new ArrayList<String>(Arrays.asList(
            "Vigor", "Lucky", "None", "None"));

    public UncommonCard(){
        super(STAT_DISTRIBUTION,
                Harpspoon.nextInt(MIN_COST, MAX_COST),
                Rarity.UNCOMMON,
                POWERS.get(Harpspoon.nextInt(0, POWERS.size() - 1)));
    }
}
