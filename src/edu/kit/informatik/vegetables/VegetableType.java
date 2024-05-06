package edu.kit.informatik.vegetables;

import edu.kit.informatik.main.Main;

/**
 * This is a enum class of all possible vegetables (with growth time as a parameter) in the game
 * @author ubvaa
 * @version 1.1
 */
public enum VegetableType implements IVegetableType {
    /**
     * enum for carrot
     */
    Carrot (1),
    /**
     * enum for salad
     */
    Salad (2),
    /**
     * enum for tomato
     */
    Tomato (3),
    /**
     * enum for mushroom
     */
    Mushroom (4);
    
    /**
     * Growth time of the vegetable
     */
    private final int growthTime;
    
    /**
     * Constructor of a vegetable type
     * @param growthTime
     */
    VegetableType(int growthTime) {
        this.growthTime = growthTime;
    }
    
    @Override
    public String getNameSingular() {
        return this.name().toLowerCase();
    }
    
    @Override
    public String getNamePlural() {
        String result = this.getNameSingular();
        switch (result) {
            case "tomato":
                result += "es";
                break;
            default:
                result += "s";
                break;
        }
        
        return result;
    }
    
    @Override
    public int getGrowthTime() {
        return growthTime;
    }
    
    /**
     * Helping method, that converts a String to vegetable type, if it's possible.
     * @param input - input String
     * @return - vegetable type, if input corresponds to it, else - null 
     */
    public static VegetableType stringToType(String input) {
        switch (input) {
            case Main.CARROT:
                return VegetableType.Carrot;
            case Main.TOMATO:
                return VegetableType.Tomato;
            case Main.MUSHROOM:
                return VegetableType.Mushroom;
            case Main.SALAD:
                return VegetableType.Salad;
            default:
                return null;
        }
    }
}
