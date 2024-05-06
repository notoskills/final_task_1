package edu.kit.informatik.tiles;

import java.util.ArrayList;

import edu.kit.informatik.vegetables.VegetableType;

/**
 * A class, that implements Field
 * @author ubvaa
 * @version 1.1
 */
public class Field extends Tile implements ITile {

    /**
     * List of vegetables, that can be grown on the field
     */
    private final ArrayList<VegetableType> growableVegetables = new ArrayList<VegetableType>() {{
            add(VegetableType.Carrot);
            add(VegetableType.Salad);
            add(VegetableType.Tomato);
        }};
    /**
     * Capacity of the field
     */
    private final int capacity = 4;
    /**
     * Abbreviation of the field
     */
    private final String abbreviation = "Fi";

    /**
     * Constructor of the new field
     */
    public Field() { }
    
    @Override
    public ArrayList<VegetableType> getTypes() {
        return this.growableVegetables;
    }
    
    @Override
    public String getAbbreviation() {
        return this.abbreviation;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

}