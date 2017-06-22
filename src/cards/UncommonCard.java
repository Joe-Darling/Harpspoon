package cards;

import game.MainGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe on 6/21/2017.
 *
 */
public class UncommonCard extends Card{
    private static final int statDistribution = 11;
    private static final int minCost = 2;
    private static final int maxCost = 5;
    private static final List<String> powers = new ArrayList<String>(Arrays.asList(
            "Vigor", "Lucky", "None", "None"));

    public UncommonCard(){
        super(statDistribution,
                MainGame.nextInt(minCost, maxCost),
                Rarity.UNCOMMON,
                powers.get(MainGame.nextInt(0, powers.size() - 1)));
    }
}
