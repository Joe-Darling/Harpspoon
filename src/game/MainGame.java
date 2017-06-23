package game;

import players.Human;
import players.Player;

import java.util.*;

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

    private Player player1;
    private Player player2;
    private int player1TotalScore;
    private int player2TotalScore;

    public MainGame(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        this.player1TotalScore = 0;
        this.player2TotalScore = 0;
    }

    public static void main(String[] args) {
        MainGame game;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Harpspoon!");
        System.out.print("How many human players will play? (0-2): ");
        int humans = scanner.nextInt();
        List<String> classes = new ArrayList<String>(Arrays.asList("Mage", "Mage"));
        if(humans == 2)
            game = new MainGame(new Human(), new Human());
    }
}
