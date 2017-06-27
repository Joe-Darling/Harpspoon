package cards;


import cards.Powers.*;
import game.Harpspoon;

import java.util.*;

/**
 * Created by Joe on 6/21/2017.
 */

public abstract class Card {

    public enum Rarity{
        COMMON("C"), UNCOMMON("U"), RARE("R"), LEGENDARY("L");

        private final String label;
        Rarity(String label){
            this.label = label;
        }
        public String getLabel(){
            return label;
        }

    }

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
        int extraHealth = Harpspoon.nextInt(0, statPoints);
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
            this.name = basicCardNames.get(Harpspoon.nextInt(0, basicCardNames.size() - 1));
            power = new NoPower();
        }
        else{
            this.name = rareNames.get(powerName);
            switch (powerName){
                case "Angry":
                    power = new Angry(baseHealth);
                    break;
                case "Bubble":
                    power = new Bubble();
                    break;
                case "Companion":
                    power = new Companion();
                    break;
                case "Dodge":
                    power = new Dodge();
                    break;
                case "Lucky":
                    power = new Lucky();
                    break;
                case "Mommy":
                    power = new Mommy();
                    break;
                case "Undying":
                    power = new Undying();
                    break;
                case "Vengeful":
                    power = new Vengeful(baseHealth);
                    break;
                case "Vigor":
                    power = new Vigor();
                    break;
                case "Wrath":
                    power = new Wrath();
                    break;
            }
        }
        setAbbrevRep();
    }

    // Constructor for creating card on the fly (used for spawned cards)
    public Card(String name, int baseHealth, int baseAttack, Rarity rarity, String powerName, Power power){
        this.name = name;
        this.baseHealth = baseHealth;
        currHealth = baseHealth;
        this.baseAttack = baseAttack;
        bonusAttack = 0;
        bonusResistance = 0;
        invincible = false;
        cost = 0;
        this.rarity = rarity;
        this.powerName = powerName;
        this.power = power;
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

    public void gainCurrHealth(int amount){
        currHealth += amount;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack){
        this.baseAttack = baseAttack;
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

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
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

    public void setPower(Power power){
        this.power = power;
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
        int healthBeforeHit = currHealth;
        int remainingDamage = damage - currHealth;
        if(!invincible){
            currHealth = (currHealth - damage) + bonusResistance;
            if(currHealth < 0){
                System.out.println(name + " takes " + (healthBeforeHit) + " damage!");
                currHealth = 0;
            }
            else
                System.out.println(name + " take " + (damage - bonusResistance) + " damage!");
            bonusResistance = 0;
        }
        else{
            System.out.println(name + " takes no damage!");
            invincible = false;
        }
        if(remainingDamage < 0)
            return 0;
        return remainingDamage;
    }

    public String inGamePrint(){
        return name + ", a " + cost + " mana " + baseAttack + "/" + currHealth + ". with power: " + powerName;
    }

    public String toString(){
        return name + " is a " + cost + " mana " + baseAttack + "/" + currHealth +
                ", rarity: " + rarity + ", power: " + powerName + ", abbrev: " + abbrevRep;
    }
}
