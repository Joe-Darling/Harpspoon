package cards;


import cards.Powers.Lucky;
import cards.Powers.Power;
import cards.Powers.Vigor;
import game.MainGame;
import players.Player;

import java.util.*;

/**
 * Created by Joe on 6/21/2017.
 */
enum Rarity{
    COMMON("C"), UNCOMMON("U"), RARE("R"), LEGENDARY("L");

    private final String label;
    Rarity(String label){
        this.label = label;
    }
    public String getLabel(){
        return label;
    }

}

public abstract class Card {

    // Names for cards without abilities.
    private final List<String> basicCardNames = new ArrayList<String>(Arrays.asList(
            "Henry", "Jenn", "Carrie", "William", "Samantha", "Michelle", "Barry",
            "Michael", "Daniel", "Victoria", "Ben", "Patrick", "Edward", "Nick",
            "Lenard", "George", "Mary", "Alyssa", "Ted", "Robin"));

    // Names for cards with abilities. Each ability is associated with a name.
    private final Map<String, String> rareNames = createMap();
    private Map<String, String> createMap(){
        Map<String, String> map = new HashMap<>();
        map.put("Vigor", "Russel - The Conquer");
        map.put("Lucky", "Victor - The Victor");
        map.put("Dodge", "Anem - The Mobile");
        map.put("Undying" , "Alec - The Undying");
        map.put("Angry", "Chris - The Forgotten");
        map.put("Wrath", "Logan - The Unhinged");
        map.put("Mommy!", "Joe - The Destroyer of Worlds");
        map.put("Bubble", "Sola - The Defender of Justice");
        map.put("Companion", "Cassandra - The Protector");
        map.put("Vengeful", "Karis - The Forsaken");

        return map;
    }

    private String name;
    private int baseHealth;
    private int currHealth;
    private int baseAttack;
    private int bonusAttack; // Used for temporary buffs
    private int bonusResistance;
    private boolean invincible;
    private int cost;
    private Rarity rarity;
    private String powerName;
    private Power power;
    private String abbrevRep;

    public Card(int statPoints, int cost, Rarity rarity, String powerName){
        // In this block we first get a random amount of health to add between 0 and the max amount - 1
        // We deduct 1 because at least 1 health point must go into the health of the card so it can't be random
        // Next we set the base and current health to the randomly generated number plus the 1 constant health
        // Finally we set the attack to the rest of the unused stat points (remainder = total - used)
        int extraHealth = MainGame.nextInt(0, statPoints);
        this.baseHealth = 1 + extraHealth;
        this.currHealth = baseHealth;
        this.baseAttack = statPoints - extraHealth;

        this.bonusAttack = 0;
        this.bonusResistance = 0;
        this.invincible = false;
        this.cost = cost;
        this.rarity = rarity;
        this.powerName = powerName;
        if(powerName.equals("None")) {
            this.name = basicCardNames.get(MainGame.nextInt(0, basicCardNames.size() - 1));
            power = null;
        }
        else{
            this.name = rareNames.get(powerName);
            switch (powerName){
                case "Vigor":
                    power = new Vigor();
                    break;
                case "Lucky":
                    power = new Lucky();
            }
        }

        setAbbrevRep();

    }

    public String getName() {
        return name;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getCurrHealth(){
        return currHealth;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBonusAttack() {
        return bonusAttack;
    }

    public void setBonusAttack(int bonus){
        bonusAttack += bonus;
    }

    public int getBonusResistance() {
        return bonusResistance;
    }

    public void setBonusResistance(int bonusResistance) {
        this.bonusResistance = bonusResistance;
    }

    public int getCost() {
        return cost;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public String getPowerName(){
        return powerName;
    }

    public Power getPower(){
        return power;
    }

    public String getAbbrevRep() {
        return abbrevRep;
    }

    public void setAbbrevRep() {
        String abbrevRep = "";
        abbrevRep += rarity.getLabel() + Character.toString(powerName.charAt(0)) + ":";
        if(cost < 10)
            abbrevRep += "0" + Integer.toString(cost) + ":";
        else
            abbrevRep += Integer.toString(cost) + ":";

        if(baseAttack < 10)
            abbrevRep += "0" + Integer.toString(baseAttack) + ":";
        else
            abbrevRep += Integer.toString(baseAttack) + ":";

        if(currHealth < 10)
            abbrevRep += "0" + Integer.toString(currHealth);
        else
            abbrevRep += Integer.toString(currHealth);
        this.abbrevRep = abbrevRep;
    }

    public int sustainDamage(int damage){
        int remainingDamage = damage - currHealth;
        currHealth -= damage;
        if(currHealth < 0){
            currHealth = 0;
        }
        if(remainingDamage < 0)
            return 0;
        return remainingDamage;
    }

    public void prepare(){
        if(power.shouldEffectTrigger(this, Power.CardState.TURN_START))
            power.triggerEffect(this);
        bonusAttack = 0;
        bonusResistance = 0;
    }

    public Card spawnCard(){ // Currently wrong. Would use them at incorrect times.
        if(powerName.equals("Companion"))
            return new CommonCard(); // Replace with legendary when at that step
        else if(powerName.equals("Mommy!"))
            return new CommonCard();
        return null;
    }

    public String toString(){
        return name + " is a " + cost + " mana " + baseAttack + "/" + baseHealth +
                ", rarity: " + rarity + ", power: " + powerName + ", abbrev: " + abbrevRep;
    }
}
