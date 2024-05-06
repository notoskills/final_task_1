package edu.kit.informatik.tiles;

import java.util.ArrayList;

import edu.kit.informatik.vegetables.VegetableType;

/**
 * A class, that implements Garden
 * @author ubvaa
 * @version 1.1
 */
public class Garden extends Tile implements ITile {
    
    /**
     * List of the vegetables, that can be grown on the garden
     */
    private final ArrayList<VegetableType> growableVegetables = new ArrayList<VegetableType>() {{
            add(VegetableType.Carrot);
            add(VegetableType.Salad);
            add(VegetableType.Tomato);
            add(VegetableType.Mushroom);
        }};
    /**
     * Capacity of the garden
     */
    private final int capacity = 2;
    /**
     * Abbreviation of the garden
     */
    private final String abbreviation = "G";
    
    /**
     * Constructor of the new garden
     */
    public Garden() { }
    
    @Override
    public String getAbbreviation() {
        return this.abbreviation;
    }

    @Override
    public ArrayList<VegetableType> getTypes() {
        return this.growableVegetables;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }
}
