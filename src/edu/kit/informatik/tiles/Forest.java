package edu.kit.informatik.tiles;

import java.util.ArrayList;

import edu.kit.informatik.vegetables.VegetableType;

/**
 * A class, that implements Forest
 * @author ubvaa
 * @version 1.1
 */
public class Forest extends Tile implements ITile {
    
    /**
     * List of vegetables, that can be grown on the forest
     */
    private final ArrayList<VegetableType> growableVegetables = new ArrayList<VegetableType>() {{
            add(VegetableType.Carrot);
            add(VegetableType.Mushroom);
        }};
    /**
     * Capacity of the forest
     */
    private final int capacity = 4;
    /**
     * Abbreviation of the forest
     */
    private final String abbreviation = "Fo";
    
    /**
     * Constructor of the new Forest
     */
    public Forest() { }
    
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