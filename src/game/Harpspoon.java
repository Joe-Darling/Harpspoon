package game;

import cards.Card;
import cards.CommonCard;
import cards.Powers.Power;
import players.Human;
import players.Mage;
import players.Player;

import java.util.*;

/**
 * Created by Joe on 6/21/2017.
 */
public class Harpspoon {
    /** the single instance of the random number generator */
    public final static Random rng = new Random();

    /** the seed the random number generator will use */
    public final static int SEED = 0;
    public static int nextInt(int min, int max) {
        return rng.nextInt(max - min + 1) + min;
    }

    private Scanner scanner;

    private Player player1;
    private Player player2;
    private int player1TotalScore;
    private int player2TotalScore;

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
            player2 = getPlayer();
        }
        else if(humans == 0){
            player1 = getPlayer();
            player2 = getPlayer();
        }
        else{
            System.out.println("Invalid input, exiting game.");
            System.exit(0);
        }

        if(player1.getName().equals(player2.getName())){
            System.out.println("Both players have the same name, player two's name is adjusted for clarity");
            player2.setName(player2.getName() + "2");
        }
    }

    private Player getPlayer(){
        System.out.print("Enter class for AI player: ");
        String aiClass = scanner.next().toLowerCase();
        scanner.nextLine();
        System.out.print("How many games will the ai play for: ");
        int gamesToPlay = scanner.nextInt();
        System.out.print("\n");
        switch(aiClass){
            case "mage":
                return new Mage(gamesToPlay);
            default:
                break;
        }
        return null;
    }

    private void game(){
        while(player1.getCurrScore() > 0 && player2.getCurrScore() > 0){
            // At the beginning of each round both players call their new round method
            player1.newRound();
            player2.newRound();

            // Then for each card in play we call their prepare method
            for(Card card : player1.getCardsInPlay())
                card.prepare();
            for(Card card : player2.getCardsInPlay())
                card.prepare();

            // We then display the current field state.
            displayField();

            // Next we allow each player to take their turn, starting with player one.
            takeTurn(player1);
            takeTurn(player2);

            // Finally we battle with the cards both players have summoned.
            battle();
        }
        if(player1.continuePlaying() && player2.continuePlaying()){
            player1.newGame();
            player2.newGame();
            game();
        }
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    private void takeTurn(Player player){
        Card card;
        do{
            card = player.playACard();
            if(card != null) {
                player.summonCard(card);
                if (card.getPower().shouldEffectTrigger(card, Power.CardState.ON_SPAWN)){
                    player.summonCard(card.getPower().triggerEffect(card));
                    pauseForDramaticEffect();
                }
            }
        }while(card != null && player.getCurrMana() > 0 && player.getHand().size() > 0);
    }

    private void battle(){
        int player1Damage = 0;
        int player2Damage = 0;
        // First we tally up the damage on call check if any effects trigger on attack
        for(Card card : player1.getCardsInPlay()){
            if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_ATTACK)){
                card.getPower().triggerEffect(card);
                pauseForDramaticEffect();
            }
            player1Damage += card.getBaseAttack() + card.getBonusAttack();
        }
        for(Card card : player2.getCardsInPlay()){
            if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_ATTACK)){
                card.getPower().triggerEffect(card);
                pauseForDramaticEffect();
            }
            player2Damage += card.getBaseAttack() + card.getBonusAttack();
        }
        dealDamage(player1, player2, player1Damage);
        dealDamage(player2, player1, player2Damage);
    }

    private void dealDamage(Player p1, Player p2, int playerDamage){
        if(playerDamage == 0)
            return;
        System.out.println(p1.getName() + " begins his attack!" );
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
                if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_ATTACKED)){
                    card.getPower().triggerEffect(card);
                    pauseForDramaticEffect();
                }
                if(card.getCurrHealth() == 0){
                    if(card.getPower().shouldEffectTrigger(card, Power.CardState.ON_DEATH)){
                        p2.summonCard(card.getPower().triggerEffect(card));
                        pauseForDramaticEffect();
                    }
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
        System.out.print(player2.getName() + "'s Board: ");
        for(Card card : player2.getCardsInPlay()){
            card.setAbbrevRep();
            System.out.print(card.getAbbrevRep() + " ");
        }
        System.out.println();
        System.out.print(player1.getName() + "'s Board: ");
        for(Card card : player1.getCardsInPlay()){
            card.setAbbrevRep();
            System.out.print(card.getAbbrevRep() + " ");
        }
        System.out.print("\n" + player1.getName() + " has " + player1.getCurrScore() + " health left. ");
        System.out.println(player2.getName() + " has " + player2.getCurrScore() + " health left.\n");
        pauseForDramaticEffect();
    }

    private void pauseForDramaticEffect(){
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        //rng.setSeed(80);
        System.out.println("Welcome to Harpspoon!");
        Harpspoon harpspoon = new Harpspoon();
        harpspoon.game();
    }
}
