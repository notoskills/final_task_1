package edu.kit.informatik.vegetables;

/**
 * Interface for vegetables in the game
 * @author ubvaa
 * @version 1.1
 */
public interface IVegetableType {

    /**
     * Method, that generates vegetable's name in the singular form
     * @return this name as a String
     */
    String getNameSingular();
    
    /**
     * Method, that generates vegetable's name in the plural form
     * @return this name as a String
     */
    String getNamePlural();
    
    /**
     * Method, that gets vegetable's growth time
     * @return this growth time
     */
    int getGrowthTime();
}
