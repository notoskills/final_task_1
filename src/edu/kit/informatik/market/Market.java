package edu.kit.informatik.market;

import java.util.HashMap;

import edu.kit.informatik.main.Main;
import edu.kit.informatik.vegetables.VegetableType;

/**
 * This is a class for the gaming market.
 * @author ubvaa
 * @version 1.1
 */
public final class Market {

    /**
     * Array of all possible prices of mushrooms
     */
    private static final int[] MUSHROOM_PRICE = {12, 15, 16, 17, 20};
    /**
     * Array of all possible prices of carrots
     */
    private static final int[] CARROT_PRICE = {3, 2, 2, 2, 1};
    /**
     * Array of all possible prices of tomatoes
     */
    private static final int[] TOMATO_PRICE = {3, 5, 6, 7, 9};
    /**
     * Array of all possible prices of salads
     */
    private static final int[] SALAD_PRICE = {6, 5, 4, 3, 2};
    /**
     * Upper bound of price indicators'
     */
    private static final int UPPER_BOUND = 4;
    /**
     * Lower bound of price indicators'
     */
    private static final int LOWER_BOUND = 0;
      
    /**
     * The first price indicator (rhombus)
     */
    private static int priceIndicator1 = 2;
    /**
     * The second price indicator (star)
     */
    private static int priceIndicator2 = 2;

    
    /**
     * Private constructor of the market
     */
    private Market() { }

    /**
     * Method, that gets a price of the vegetable.
     * @param type of vegetable, which price we want to know
     * @return price of the vegetable
     */
    public static int getPrice(VegetableType type) {
        switch (type) {
            case Carrot:
                return CARROT_PRICE[priceIndicator1];
            case Mushroom:
                return MUSHROOM_PRICE[priceIndicator1];
            case Tomato:
                return TOMATO_PRICE[priceIndicator2];
            case Salad:
                return SALAD_PRICE[priceIndicator2];
            default:
                return 0;
        }
    }
    
    
    /**
     * Method, that updates prices of the market after every selling.
     * @param soldVegs - HashMap of vegetables, that were sold during the player's turn
     */
    public static void pricesUpdate(HashMap<VegetableType, Integer> soldVegs) {
        
        int carrots = soldVegs.get(VegetableType.Carrot);
        int tomatoes = soldVegs.get(VegetableType.Tomato);
        int mushrooms = soldVegs.get(VegetableType.Mushroom);
        int salads = soldVegs.get(VegetableType.Salad);
        int newPriceIndicator1 = priceIndicator1;
        int newPriceIndicator2 = priceIndicator2;
        newPriceIndicator1 += carrots / 2 - mushrooms / 2;
        newPriceIndicator2 += salads / 2 - tomatoes / 2;
        if (newPriceIndicator1 > UPPER_BOUND) {
            newPriceIndicator1 = UPPER_BOUND;
        }
        if (newPriceIndicator2 > UPPER_BOUND) {
            newPriceIndicator2 = UPPER_BOUND;
        }
        if (newPriceIndicator1 < LOWER_BOUND) {
            newPriceIndicator2 = LOWER_BOUND;
        }
        if (newPriceIndicator2 < LOWER_BOUND) {
            newPriceIndicator2 = LOWER_BOUND;
        }
        priceIndicator1 = newPriceIndicator1;
        priceIndicator2 = newPriceIndicator2;
    }
    
    /**
     * Method, that converts market to a String.
     * @return market as a String
     */
    public static String marketToString() {
        String market = "";
        
        int largestLength = 0;
        int largestKeyLength = 0;
        int largestValueLength = 0;
        
        for (VegetableType type : VegetableType.values()) {
            int keyLength = type.getNamePlural().length();
            int valueLength = String.valueOf(getPrice(type)).length();
            
            if (keyLength > largestKeyLength) {
                largestKeyLength = keyLength;
            }
            if (valueLength > largestValueLength) {
                largestValueLength = valueLength;
            }
            
        }
        largestLength = largestKeyLength + largestValueLength;
        
        market += VegetableType.Mushroom.getNamePlural() + ": " 
                + Main.BLANK_SYMBOL.repeat(largestLength - VegetableType.Mushroom.getNamePlural().length() 
                                            - String.valueOf(getPrice(VegetableType.Mushroom)).length()) 
                + String.valueOf(getPrice(VegetableType.Mushroom)) + Main.LINE_SEPARATOR;
        
        market += VegetableType.Carrot.getNamePlural() + ": " 
                + Main.BLANK_SYMBOL.repeat(largestLength - VegetableType.Carrot.getNamePlural().length() 
                                            - String.valueOf(getPrice(VegetableType.Carrot)).length())
                + String.valueOf(getPrice(VegetableType.Carrot)) + Main.LINE_SEPARATOR;
        
        market += VegetableType.Tomato.getNamePlural() + ": " 
                + Main.BLANK_SYMBOL.repeat(largestLength - VegetableType.Tomato.getNamePlural().length() 
                                            - String.valueOf(getPrice(VegetableType.Tomato)).length()) 
                + String.valueOf(getPrice(VegetableType.Tomato)) + Main.LINE_SEPARATOR;
        
        market += VegetableType.Salad.getNamePlural() + ": " 
                + Main.BLANK_SYMBOL.repeat(largestLength - VegetableType.Salad.getNamePlural().length() 
                                            - String.valueOf(getPrice(VegetableType.Salad)).length()) 
                + String.valueOf(getPrice(VegetableType.Salad));

        return market;
    }
    
}
