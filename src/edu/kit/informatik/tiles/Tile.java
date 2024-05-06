package edu.kit.informatik.tiles;

import java.util.ArrayList;

import edu.kit.informatik.vegetables.VegetableType;

/**
 * A class, that implements tiles in the game.
 * @author ubvaa
 * @version 1.1
 */
public class Tile implements ITile {
    
    /**
     * Pool of tiles, that will be shuffled at the beginning of the game
     * and from where will a tile for purchase be pulled out.
     */
    private static ArrayList<Tile> tilesPool;
    /**
     * Type of the vegetable, that is planted on the tile
     */
    private VegetableType type;
    /**
     * Tile's countdown 
     */
    private int countdown;
    /**
     * Tile's capacity
     */
    private int capacity;
    /**
     * Tile's abbreviation
     */
    private String abbreviation;
    /**
     * Amount of vegetables on the tile
     */
    private int vegsAmount = 0;
    
    /**
     * Getter for tiles to shuffle pool.
     * @return - pool of tiles.
     */
    public static ArrayList<Tile> getTilesPool() {
        return tilesPool;
    }
    
    /**
     * Method, that creates a pool of tiles after input of players' amount.
     * @param playersAmount - amount of players in the game
     */
    public static void tilesPoolCreating(int playersAmount) {
        tilesPool = new ArrayList<>();
        for (int i = 0; i < 2 * playersAmount; i++) {
            tilesPool.add(new Garden());
        }
        for (int i = 0; i < 3 * playersAmount; i++) {
            tilesPool.add(new Field());
        }
        for (int i = 0; i < 2 * playersAmount; i++) {
            tilesPool.add(new LargeField());
        }
        for (int i = 0; i < 2 * playersAmount; i++) {
            tilesPool.add(new Forest());
        }
        for (int i = 0; i < playersAmount; i++) {
            tilesPool.add(new LargeForest());
        }
    }
    
    @Override
    public boolean allowedToPlant(VegetableType type) {    
        return !(this.getClass().equals(Barn.class)) && this.getTypes().contains(type) && this.isFree();
    }
    
    @Override
    public void plant(VegetableType type) {
        this.vegsAmount = 1;
        this.type = type;
        this.countdown = type.getGrowthTime();
        this.capacity = getCapacity();
    }
    
    @Override
    public boolean isFree() {
        return this.vegsAmount == 0;
    }
    
    @Override
    public String getAbbreviation() {
        return this.abbreviation;
    }
    
    @Override
    public ArrayList<VegetableType> getTypes() {
        return this.getTypes();
    }
    
    @Override
    public int getCapacity() {
        return capacity;
    }
    
    @Override
    public String getTypeToString() {
        return String.valueOf(this.type);
    }
    
    @Override
    public VegetableType getType() {
        return this.type;
    }
    
    @Override
    public int getVegsAmount() {
        return this.vegsAmount;
    }
    
    @Override
    public int getCountdown() {
        return this.countdown;
    }
    
    @Override
    public void harvestVegetables(int amount) {
        this.vegsAmount = this.vegsAmount - amount;
    }
    
    @Override
    public void decreaseCountdown() {
        if (this.capacity != this.vegsAmount) {
            this.countdown = this.countdown - 1;
        }
        
    }
    
    @Override
    public void increasePlantedVegs() {
        if (this.capacity < this.vegsAmount * 2) {
            this.vegsAmount = this.capacity;
            this.countdown = 0;
        } else if (!this.isFree()) {
            this.vegsAmount = this.vegsAmount * 2;
            this.countdown = this.type.getGrowthTime();
        }
    }
    
}
