package players;

import cards.Card;

import java.util.Scanner;

/**
 * Created by Joe on 6/23/2017.
 */
public class Human extends Player{

    private Scanner scanner = new Scanner(System.in);

    public Human(){
        super("");
        setName(promptName());
    }

    private String promptName(){
        System.out.print("Enter name for human player: ");
        return scanner.next();
    }

    public Card playACard() {
        System.out.println("You have " + getCurrMana() + "/" + getTotalMana() +
                " mana left this turn. Pick a card by selecting index. or enter 0 to pass.");
        for(int i = 0; i < getHand().size(); i++){
            System.out.println((i + 1) + ") " + getHand().get(i).inGamePrint());
        }
        System.out.print("Selection: ");
        int cardNum = scanner.nextInt();
        if(cardNum == 0){
            System.out.println("You pass your turn.");
            return null;
        }
        if(cardNum > getHand().size() || cardNum < 0){
            System.out.println("Invalid input, card not in range. You miss your turn.");
            return null;
        }
        Card card = getHand().get(cardNum - 1);
        if(card.getCost() > getCurrMana()){
            System.out.println("Invalid Card Selected, it costs more mana than you have. You miss your turn.");
            return null;
        }
        return card;
    }

    public boolean continuePlaying() {
        System.out.print("Play again? (Y/N): ");
        scanner.nextLine();
        return scanner.next().toUpperCase().equals("Y"); // Yes, toUpper is there because I kept forgetting.
    }
}
