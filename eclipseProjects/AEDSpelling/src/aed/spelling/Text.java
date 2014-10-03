package aed.spelling;

import aed.dataStructures.ArrayIterator;
import aed.dataStructures.Iterator;
import aed.dataStructures.List;

/**
 * Represents a text identified by its id
 *
 * @author Andre Pontes (42845)
 */

public class Text {
    private String id;
    private int nOfLines;
    private Line[] lines;

    /**
     * Create a text
     *
     * @param id Id
     * @param lines Lines list
     */
    public Text(String id, List<String> textLines){
        this.id = id;
        this.nOfLines = 0;
        this.lines = new Line[textLines.size()];
        
        for(Iterator<String> iterator = textLines.iterator(); iterator.hasNext(); ) {
        	this.lines[nOfLines++] = new Line(nOfLines, iterator.next());
        }
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
    
    /**
     * 
     * @param firstLine first line of the text
     * @param lastLine last line of the text
     * @return An iterator of lines
     */
    public Iterator<Line> lines(int firstLine, int lastLine){
    	int firstIndex = firstLine--;
    	int lastIndex = lastLine--;
    	Line[] selectedLines = new Line[lastLine - firstLine];
    	
    	int count = 0;
    	for (int i = firstIndex; i < lastIndex; i++) {
    		selectedLines[count++] = this.lines[i];
    	}
        return new ArrayIterator<Line>(selectedLines);
    }
}
