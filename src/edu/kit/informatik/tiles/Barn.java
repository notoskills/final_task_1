package edu.kit.informatik.tiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.kit.informatik.main.BarnComparator;
import edu.kit.informatik.main.Main;
import edu.kit.informatik.players.Player;
import edu.kit.informatik.vegetables.VegetableType;

/**
 * A class, that implements barn in the game
 * @author ubvaa
 * @version 1.1
 */
public class Barn extends Tile {
    /**
     * "Inventory" of the barn
     */
    private HashMap<VegetableType, Integer> inventory;
    /**
     * 
     */
    private final int startingCountdown = 6;
    /**
     * Starting inventory of each player
     */
    private final HashMap<VegetableType, Integer> startInventory = new HashMap<>() {{
            put(VegetableType.Carrot, 1);
            put(VegetableType.Mushroom, 1);
            put(VegetableType.Salad, 1);
            put(VegetableType.Tomato, 1);
        }};
    /**
     * Barn's countdown 
     */
    private int countdown;
    /**
     * Barn's abbreviation
     */
    private final String abbreviation = "B";
    
    /**
     * Constructor for new Barn
     */
    public Barn() {
        this.inventory = new HashMap<>(startInventory);
        this.countdown = startingCountdown;
    }
    
    /**
     * Method, that cleans the barn, when countdown will be 0
     */
    public void cleanBarn() {
        Set<VegetableType> vegetables = this.inventory.keySet();
        for (VegetableType vegetable : vegetables) {
            this.inventory.put(vegetable, 0);
        }
        this.countdown = startingCountdown;
    }
    
    @Override
    public void decreaseCountdown() {
        if (this.getVegsAmount() != 0) {
            this.countdown = this.countdown - 1;
        }
    }
    
    /**
     * Getter for inventory of the barn
     * @return - inventory
     */
    public HashMap<VegetableType, Integer> getInventory() {
        return this.inventory;
    }
    
    @Override
    public String getAbbreviation() {
        return abbreviation;
    }
    
    /**
     * Method, that removes vegetables from the barn after planting or selling
     * @param type - type of the vegetable to remove
     * @param amount - amount of the vegetables to remove
     */
    public void removeVegetables(VegetableType type, int amount) {
        this.inventory.put(type, this.inventory.get(type) - amount);
    }
    
    /**
     * Method, that adds vegetables to the barn after harvesting or buying
     * @param type - type of the vegetable to add
     * @param amount - amount of the vegetables to add
     */
    public void addVegetables(VegetableType type, int amount) {
        if (this.getVegsAmount() == 0) {
            this.countdown = startingCountdown;
        }
        this.inventory.put(type, this.inventory.get(type) + amount);
    }
    
    @Override
    public int getVegsAmount() {
        int vegsAmount = 0;
        for (VegetableType type : VegetableType.values()) {
            vegsAmount += this.inventory.get(type);
        }
        return vegsAmount;
    }
    
    @Override
    public int getCountdown() {
        return this.countdown;
    }

    /**
     * Method, that transforms the barn to a String
     * @param player - player, whose barn is transformed
     * @return - barn as a String
     */
    public String toString(Player player) {
        String output = "";
        String barnString = "Barn";
        if (this.getVegsAmount() != 0) {
            barnString += " (spoils in " + this.countdown + " ";
            barnString += this.countdown == 1 ? "turn" : "turns";
            barnString += ")";
        }
        barnString += Main.LINE_SEPARATOR;
        output += barnString;
        Collection<Map.Entry<String, Integer>> entries = sortBarn(this.inventory);

        String goldString = "Gold: ";
        String goldAmount = String.valueOf(player.getGold());
        String sumString = "Sum: ";
        String sumAmount = String.valueOf(this.getVegsAmount());
        
        int largestKeyLength = 0;
        int largestValueLength = 0;
        for (Map.Entry<String, Integer> e : entries) {
            if (e.getValue() == 0) {
                continue;
            }
            int keyLength = e.getKey().length();
            int valueLength = String.valueOf(e.getValue()).length();
            if (keyLength > largestKeyLength) {
                largestKeyLength = keyLength;
            }
            if (valueLength > largestValueLength) {
                largestValueLength = valueLength;
            }
        }
        
        if (String.valueOf(goldAmount).length() > largestValueLength) {
            largestValueLength = String.valueOf(goldAmount).length();
        }
        
        if (String.valueOf(sumAmount).length() > largestValueLength) {
            largestValueLength = String.valueOf(sumAmount).length();
        }
        
        int largestLength = largestKeyLength + largestValueLength;
        
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> e : entries) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            int keyLength = e.getKey().length();
            int valueLength = String.valueOf(e.getValue()).length();
            if (e.getValue() == 0) {
                continue;
            }
            int stringLength = keyLength + valueLength;
            sb.append(e.getKey()).append(Main.BLANK_SYMBOL.repeat(largestLength - stringLength)).append(e.getValue());
        }
        
        if (this.getVegsAmount() != 0) {
            sb.append(Main.LINE_SEPARATOR).append(Main.BARN_SEPARATOR.repeat(largestLength))
            .append(Main.LINE_SEPARATOR).append(sumString)
            .append(Main.BLANK_SYMBOL.repeat(largestLength - sumString.length() - sumAmount.length()))
            .append(sumAmount).append(Main.LINE_SEPARATOR.repeat(2)).append(goldString)
            .append(Main.BLANK_SYMBOL.repeat(largestLength - goldString.length() - goldAmount.length()))
            .append(goldAmount);
        } else {
            sb.append(goldString).append(goldAmount);
        }
        
        output += sb;
        return output;
    }
    
    /**
     * Sorting method of the barn.
     * @param barn - barn to sort
     * @return - sorted collections of barn vegetables and their amounts
     */
    private Collection<Map.Entry<String, Integer>> sortBarn(HashMap<VegetableType, Integer> barn) {
        HashMap<String, Integer> map = new HashMap<>();
        for (VegetableType type : VegetableType.values()) {
            map.put(type.getNamePlural() + ": ", barn.get(type));
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort(new BarnComparator());
        
        return entries;
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof Barn;
    }
    
}
