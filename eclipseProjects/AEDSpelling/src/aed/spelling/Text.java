package aed.spelling;

import aed.dataStructures.ArrayIterator;
import aed.dataStructures.Iterator;

/**
 * Represents a text identified by its id
 *
 * @author Andre Pontes (42845)
 */

public class Text {
    private String id;
    private Line[] lines;

    /**
     * Create a text
     *
     * @param id Id
     * @param lines Lines list
     */
    public Text(String id, Line[] lines){
        this.id = id;
        this.lines = lines;
    }

    /**
     * Return id
     *
     * @return Id
     */
    public String getId(){
        return this.id;
    }

    /**
     * Return lines
     *
     * @return Lines
     */
    public Iterator<Line> lines(){
        return new ArrayIterator<Line>(this.lines);
    }
}
