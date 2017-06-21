package cards;

import game.MainGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe on 6/21/2017.
 */
public class CommonCard extends Card{
    private static final int statDistribution = 7;
    private static final int minCost = 1;
    private static final int maxCost = 3;

    public CommonCard(){
        super(statDistribution,
                MainGame.nextInt(minCost, maxCost),
                Rarity.COMMON,
                "None");
    }

}
