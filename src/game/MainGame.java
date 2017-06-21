package game;

import java.util.Random;

/**
 * Created by Joe on 6/21/2017.
 */
public class MainGame {
    /** the single instance of the random number generator */
    public final static Random rng = new Random();

    /** the seed the random number generator will use */
    public final static int SEED = 0;

    public static int nextInt(int min, int max) {
        return rng.nextInt(max - min + 1) + min;
    }
}
