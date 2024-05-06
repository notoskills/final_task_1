package edu.kit.informatik.main;

import java.util.Comparator;
import java.util.Map;

/**
 * Comparator, that helps to sort a barn buy amount and by the vegetable name.
 * @author ubvaa
 * @version 1.1
 */    
public class BarnComparator implements Comparator<Map.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
        int reverseValueCompared = Integer.compare(e1.getValue(), e2.getValue());
        if (reverseValueCompared == 0) {
            return e1.getKey().compareTo(e2.getKey());
        }
        return reverseValueCompared;
    }
}