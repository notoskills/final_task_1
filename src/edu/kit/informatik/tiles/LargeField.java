package edu.kit.informatik.tiles;

import java.util.ArrayList;

import edu.kit.informatik.vegetables.VegetableType;

/**
 * A class, that implements Large Field
 * @author ubvaa
 * @version 1.1
 */
public class LargeField extends Tile implements ITile {

    /**
     * List of vegetables, that can be grown on the large field
     */
    private final ArrayList<VegetableType> growableVegetables = new ArrayList<VegetableType>() {{
            add(VegetableType.Carrot);
            add(VegetableType.Salad);
            add(VegetableType.Tomato);
        }};
    /**
     * Capacity of the large field
     */
    private final int capacity = 8;
    /**
     * Abbreviation of the large field
     */
    private final String abbreviation = "LFi";
    
    /**
     * Constructor of the large field
     */
    public LargeField() { }
    
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