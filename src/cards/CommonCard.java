package cards;

import game.MainGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe on 6/21/2017.
 */
public class CommonCard extends Card{
    private static final int STAT_DISTRIBUTION = 7;
    private static final int MIN_COST = 1;
    private static final int MAX_COST = 3;

    public CommonCard(){
        super(STAT_DISTRIBUTION,
                MainGame.nextInt(MIN_COST, MAX_COST),
                Rarity.COMMON,
                "None");
    }
}
