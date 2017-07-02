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
            default:
                System.out.println("Invalid input, exiting game.");
                System.exit(0);
                break;
        }
        return null;
    }

    private boolean game(){
        player1.newGame();
        player2.newGame();
        player1.drawCards(4);
        player2.drawCards(4);
        while(player1.getCurrScore() > 0 && player2.getCurrScore() > 0){
            // At the beginning of each round both players call their new round method
            reporter.addStringToReport("ROUND STARTED");
            player1.newRound();
            player2.newRound();
            reporter.addPlayerStats(this);
            player1.drawCards(1);
            player2.drawCards(1);

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
        int player1Damage = 0;
        int player2Damage = 0;
        // First we tally up the damage and check if any effects trigger on attack
        for(Card card : player1.getCardsInPlay()){
            if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_ATTACK)){
                card.getPower().triggerEffect(card);
                pauseForDramaticEffect();
            }
            player1Damage += card.getBaseAttack() + card.getBonusAttack();
            card.setBonusAttack(0);
        }
        reporter.addStringToReport(player1.dispCardsInPlay());
        reporter.addStringToReport("DAMAGE CALCULATED:" + player1.getName() + ":total damage=" + player1Damage);
        for(Card card : player2.getCardsInPlay()){
            if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_ATTACK)){
                card.getPower().triggerEffect(card);
                pauseForDramaticEffect();
            }
            player2Damage += card.getBaseAttack() + card.getBonusAttack();
            card.setBonusAttack(0);
        }
        reporter.addStringToReport(player2.dispCardsInPlay());
        reporter.addStringToReport("DAMAGE CALCULATED:" + player2.getName() + ":total damage=" + player2Damage);

        dealDamage(player1, player2, player1Damage);
        dealDamage(player2, player1, player2Damage);
    }

    private void dealDamage(Player p1, Player p2, int playerDamage){
        if(playerDamage == 0)
            return;
        System.out.println(p1.getName() + " begins his attack!" );
        pauseForDramaticEffect();
        for(int ind = 0; playerDamage > 0; ind++){
            if(ind >= p2.getCardsInPlay().size()){
                p2.loseHealth(playerDamage);
                playerDamage = 0;
                pauseForDramaticEffect();
            }
            else{
                Card card = p2.getCardsInPlay().get(ind);
                if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_TARGETED)){
                    card.getPower().triggerEffect(card);
                    pauseForDramaticEffect();
                }
                playerDamage = card.sustainDamage(playerDamage);
                reporter.addStringToReport("CARD DAMAGED:" + p2.getName() + ":" + card.getAbbrevRep() +
                "damage remaining=" + playerDamage);
                if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_ATTACKED)){
                    card.getPower().triggerEffect(card);
                    pauseForDramaticEffect();
                }
                if(card.getCurrHealth() == 0){
                    if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_DEATH)){
                        p2.summonCard(card.getPower().triggerEffect(card));
                        pauseForDramaticEffect();
                    }
                    reporter.addStringToReport("CARD DISCARDED:" + p2.getName() + ":" + card.getAbbrevRep());
                    p2.discardCard(card);
                    ind--;
                }
                pauseForDramaticEffect();
            }
        }
        System.out.print("\n");
    }

    private void displayField(){
        // We print player twos field first so that p1 (who will be human in 1 human vs. 1 ai) will be on bottom
        System.out.println("Turn: " + ++turnCounter);
        System.out.print(player1.getName() + "'s Board: ");
        for(Card card : player1.getCardsInPlay()){
            card.setAbbrevRep();
            System.out.print(card.getAbbrevRep() + " ");
        }
        System.out.println();
        System.out.print(player2.getName() + "'s Board: ");
        for(Card card : player2.getCardsInPlay()){
            card.setAbbrevRep();
            System.out.print(card.getAbbrevRep() + " ");
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
            System.out.println("Report of the game:");
            reporter.printReport();
        }while (playAgain);
        System.out.println("Thanks for playing!");
    }
}
