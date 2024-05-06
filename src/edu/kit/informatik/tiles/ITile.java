package edu.kit.informatik.tiles;

import java.util.ArrayList;

import edu.kit.informatik.vegetables.VegetableType;

/**
 * Interface for all the tiles in the game
 * @author ubvaa
 * @version 1.1
 */

public interface ITile {

    /**
     * Method, that proves, if player is allowed to plant a vegetable on the tile.
     * @param type - type of the vegetable to be planted
     * @return - true, if allowed; false, if not
     */
    boolean allowedToPlant(VegetableType type);
    
    /**
     * Method, that "plants" a vegetable on the tile.
     * @param type - type of the vegetable to be planted
     */
    void plant(VegetableType type);
    
    /**
     * Method, that proves, if tile is free.
     * @return - true, if free; false, if not
     */
    boolean isFree();
    
    /**
     * Method, that gets abbreviation of the tile for the board output.
     * @return - abbreviation
     */
    String getAbbreviation();
    
    /**
     * Getter for capacity of the tile.
     * @return - tile's capacity
     */
    int getCapacity();
    
    /**
     * Getter for vegetables' types, that are allowed to plant on the tile.
     * @return - types of the vegetables
     */
    ArrayList<VegetableType> getTypes();
    
    /**
     * Method, that gets type of the tile as a String.
     * @return - type of the tile as a String
     */
    String getTypeToString();
    
    /**
     * Getter for tile's type.
     * @return - type of the tile
     */
    VegetableType getType();
    
    /**
     * Getter for amount of the vegetables on the tile.
     * @return - amount of the vegetables
     */
    int getVegsAmount();
    
    /**
     * Getter for countdown of the tile.
     * @return - countdown of the tile
     */
    int getCountdown();
    
    /**
     * Method, that "harvests" vegetables in the tile by decreasing their amount 
     * @param amount - vegetables to be harvested 
     */
    void harvestVegetables(int amount);
    
    /**
     * Method, that decreases tile's countdown after each round
     */
    void decreaseCountdown();
    
    /**
     * Method, that increases planted amount of the vegetables on the tile, if it's possible
     */
    void increasePlantedVegs();
}
