package game;

import cards.Card;
import cards.CommonCard;
import cards.Powers.Power;
import players.*;

import java.util.*;

/**
 * Created by Joe on 6/21/2017.
 */
public class Harpspoon {
    // The single instance of the random number generator
    // Made static so test classes can use them for consistency.
    public final static Random rng = new Random();
    public final static int SEED = 0;
    public static int nextInt(int min, int max) {
        return rng.nextInt(max - min + 1) + min;
    }

    // The single instance of the reporter Object.
    // Reporter Made static so all classes can add to report without having to constantly pass in reporter object.
    public static Reporter reporter = new Reporter();

    private Scanner scanner;

    private Player player1;
    private Player player2;
    private int player1TotalScore;
    private int player2TotalScore;
    private boolean pauseForDrama;
    private int turnCounter;
    private List<Player> players;


    public Harpspoon(){
        scanner = new Scanner(System.in);
        System.out.print("How many human players will play? (0-2): ");
        int humans = scanner.nextInt();
        if(humans == 2){
            player1 = new Human();
            player2 = new Human();
        }
        else if(humans == 1){
            player1 = new Human();
            player2 = createNewComputerPlayer();
        }
        else if(humans == 0){
            player1 = createNewComputerPlayer();
            player2 = createNewComputerPlayer();
        }
        else{
            System.out.println("Invalid input, exiting game.");
            System.exit(0);
        }
        players = new ArrayList<>(Arrays.asList(player1, player2));
        if(player1.getName().equals(player2.getName())){
            System.out.println("Both players have the same name, player names are adjusted for clarity");
            player1.setName(player1.getName() + "-1");
            player2.setName(player2.getName() + "-2");
        }
        scanner.nextLine();
        System.out.print("Pause for dramatic effect? (Y/N): ");
        pauseForDrama = scanner.next().toUpperCase().equals("Y");
        System.out.print("\n");
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    public int getPlayer1TotalScore(){
        return player1TotalScore;
    }

    public int getPlayer2TotalScore(){
        return player2TotalScore;
    }

    private Player createNewComputerPlayer(){
        System.out.print("Enter class for AI player: ");
        String aiClass = scanner.next().toLowerCase();
        scanner.nextLine();
        System.out.print("How many games will the ai play for: ");
        int gamesToPlay = scanner.nextInt();
        System.out.print("\n");
        switch(aiClass){
            case "mage":
                return new Mage(gamesToPlay);
            case "cleric":
                return new Cleric(gamesToPlay);
            case "fighter":
                return new Fighter(gamesToPlay);
            case "dragon":
                return new Dragon(gamesToPlay);
            default:
                System.out.println("Invalid input, exiting game.");
                System.exit(0);
                break;
        }
        return null;
    }

    private boolean game(){
        turnCounter = 0;
        for(Player player : players){
            player.newGame();
            reporter.addStringToReport("PLAYER DECK CREATED");
            reporter.addStringToReport(player.dispDeck());
        }
        // I had to remove the draw lines from the for loop (and some other for loops) so the reporter output matches.
        player1.drawCards(4);
        player2.drawCards(4);
        while(player1.getCurrScore() > 0 && player2.getCurrScore() > 0){
            // At the beginning of each round both players call their new round method
            reporter.addStringToReport("ROUND STARTED");
            System.out.println("Turn: " + ++turnCounter);
            for(Player player : players){
                player.newRound();
                if(player.getDeck().size() > 0)
                    player.drawCards(1);
            }
            // Because the game can theoretically go forever if both players exhaust all their cards we check for
            // both players having an empty hand and board and if so result the game in a draw. (by setting hp to 0)
            if(player1.getHand().size() == 0 && player2.getHand().size() == 0 && player1.getCardsInPlay().size() == 0
                    && player2.getCardsInPlay().size() == 0){
                player1.setCurrScore(0);
                player2.setCurrScore(0);
            }

            reporter.addPlayerStats(this);

            // Then for each card in play we check if they have a turn start effect
            for(Card card : player1.getCardsInPlay())
                if(card.getPower().shouldEffectTrigger(card, Power.CardState.TURN_START))
                    card.getPower().triggerEffect(card);
            for(Card card : player2.getCardsInPlay())
                if(card.getPower().shouldEffectTrigger(card, Power.CardState.TURN_START))
                    card.getPower().triggerEffect(card);

            // We then display the current field state.
            displayField();

            // Next we allow each player to take their turn, starting with player one.
            takeTurn(player1);
            takeTurn(player2);

            // Finally we battle with the cards both players have summoned.
            battle();
        }
        if(player1.getCurrScore() == player2.getCurrScore()) { // If the game was a draw
            // Both players get one total point
            player1TotalScore++;
            player2TotalScore++;
        }
        else{
            // Both players total score are incremented by the result of the match
            player1TotalScore += player1.getCurrScore();
            player2TotalScore += player2.getCurrScore();
        }
        reporter.addStringToReport("GAME OVER, MAN");
        reporter.addPlayerStats(this);
        System.out.println("Report of the game:");
        reporter.printReport();
        return player1.continuePlaying() && player2.continuePlaying();
    }

    private void takeTurn(Player player){
        Card card;
        reporter.addStringToReport(player.dispHand());
        do{
            card = player.playACard();
            if(card != null) {
                player.summonCard(card);
                pauseForDramaticEffect();
                if (card.getPower().shouldEffectTrigger(card, Power.CardState.ON_SPAWN)){
                    player.summonCard(card.getPower().triggerEffect(card));
                    pauseForDramaticEffect();
                }
            }
        }while(card != null && player.getCurrMana() > 0 && player.getHand().size() > 0);
        System.out.print("\n");
    }

    private void battle(){
        // First we tally up the damage and check if any effects trigger on attack
        for(Player player : players){
            for(Card card : player.getCardsInPlay()){
                if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_ATTACK)){
                    card.getPower().triggerEffect(card);
                    pauseForDramaticEffect();
                }
                player.setTotalDamage(player.getTotalDamage() + card.getBaseAttack() + card.getBonusAttack());
                card.setBonusAttack(0);
            }
            reporter.addStringToReport(player.dispCardsInPlay());
            reporter.addStringToReport("DAMAGE CALCULATED:" + player.getName() +
                    ":total damage=" + player.getTotalDamage());
        }

        dealDamage(player1, player2);
        dealDamage(player2, player1);
    }

    private void dealDamage(Player attacker, Player attacked){
        if(attacker.getTotalDamage() == 0)
            return;
        System.out.println(attacker.getName() + " begins his attack!" );
        pauseForDramaticEffect();
        for(int ind = 0; attacker.getTotalDamage() > 0; ind++){
            if(ind >= attacked.getCardsInPlay().size()){
                attacked.loseHealth(attacker.getTotalDamage());
                attacker.setTotalDamage(0);
                pauseForDramaticEffect();
            }
            else{
                Card card = attacked.getCardsInPlay().get(ind);
                if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_TARGETED)){
                    card.getPower().triggerEffect(card);
                    pauseForDramaticEffect();
                }
                attacker.setTotalDamage(card.sustainDamage(attacker.getTotalDamage()));
                reporter.addStringToReport("CARD DAMAGED:" + attacked.getName() + ":" + card.getAbbrevRep() +
                "damage remaining=" + attacker.getTotalDamage());
                if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_ATTACKED)){
                    card.getPower().triggerEffect(card);
                    pauseForDramaticEffect();
                }
                if(card.getCurrHealth() == 0){
                    if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_DEATH)){
                        attacked.summonCard(card.getPower().triggerEffect(card));
                        pauseForDramaticEffect();
                    }
                    reporter.addStringToReport("CARD DISCARDED:" + attacked.getName() + ":" + card.getAbbrevRep());
                    attacked.discardCard(card);
                    ind--;
                }
                pauseForDramaticEffect();
            }
        }
        System.out.print("\n");
    }

    private void displayField(){
        // We print player twos field first so that p1 (who will be human in 1 human vs. 1 ai) will be on bottom
        for(Player player : players){
            System.out.print(player.getName() + "'s Board: ");
            for(Card card : player.getCardsInPlay()){
                card.setAbbrevRep();
                System.out.print(card.getAbbrevRep() + " ");
            }
            System.out.println();
        }
        System.out.print("\n" + player1.getName() + " has " + player1.getCurrScore() + " health left. ");
        System.out.println(player2.getName() + " has " + player2.getCurrScore() + " health left.\n");
        pauseForDramaticEffect();
    }

    private void pauseForDramaticEffect(){
        if(pauseForDrama){
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        //rng.setSeed(80);
        System.out.println("Welcome to Harpspoon!");
        Harpspoon harpspoon = new Harpspoon();
        boolean playAgain;
        do{
            reporter.newReport(harpspoon);
            playAgain = harpspoon.game();
            // We reset the score here because of formatting issues in the reporter
            for(Player player : harpspoon.players){
                player.setCurrScore(player.getSTARTING_SCORE());
            }
        }while (playAgain);
        System.out.println(harpspoon.player1TotalScore);
        System.out.println(harpspoon.player2TotalScore);
        System.out.println("Thanks for playing!");
    }
}
