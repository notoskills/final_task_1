package edu.kit.informatik.tiles;

import java.util.ArrayList;

import edu.kit.informatik.vegetables.VegetableType;

/**
 * A class, that implements Large Forest
 * @author ubvaa
 * @version 1.1
 */
public class LargeForest extends Tile implements ITile {
    /**
     * List of vegetables, that can be grown on the large forest
     */
    private final ArrayList<VegetableType> growableVegetables = new ArrayList<VegetableType>() {{
            add(VegetableType.Carrot);
            add(VegetableType.Mushroom);
        }};
    /**
     * Capacity of the large forest
     */
    private final int capacity = 8;
    /**
     * Abbreviation of the large forest
     */
    private final String abbreviation = "LFo";
    
    /**
     * Constructor of the new large forest
     */
    public LargeForest() { }

    @Override
    public String getAbbreviation() {
        return this.abbreviation;
    }
    
    @Override
    public int getCapacity() {
        return this.capacity;
    }
    
    @Override
    public ArrayList<VegetableType> getTypes() {
        return this.growableVegetables;
    }
    
}
