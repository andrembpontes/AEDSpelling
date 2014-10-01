package aed.spelling;

import aed.dataStructures.Iterator;
import aed.dataStructures.List;

/**
 * Represents a text identified by its id
 *
 * @author Andre Pontes (42845)
 */

public class Text {
    private String id;
    private List<Line> lines;

    /**
     * Create a text
     *
     * @param id Id
     * @param lines Lines list
     */
    public Text(String id, List<Line> lines){
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
        return this.lines.iterator();
    }
}
