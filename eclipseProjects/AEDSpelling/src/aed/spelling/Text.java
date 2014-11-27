package aed.spelling;

import aed.dataStructures.*;

import java.io.Serializable;

/**
 * Represents a text identified by its id
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public class Text implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	private String				id;
	private ArrayList<Line>		lines;
	
	/**
	 * Create a text
	 *
	 * @param id Id
	 * @param textLines Lines list
	 */
	public Text(String id, List<String> textLines) {
		this.id = id;
		this.lines = new ArrayList<Line>(textLines.size());
		
		for (Iterator<String> iterator = textLines.iterator(); iterator.hasNext();)
			this.lines.addLast(new Line(this.lines.size() + 1, iterator.next()));
	}
	
	/**
	 * Return id
	 *
	 * @return Id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Returns an iterator of text lines between two specified boundaries
	 * 
	 * @param firstLine first line of the text
	 * @param lastLine last line of the text
	 * @return An iterator of lines
	 */
	private Iterator<Line> getLinesIterator(int firstLine, int lastLine) throws InvalidLineNumberException, InvalidLineRangeException {
        return new LinesIterator<Line>(this.lines, firstLine - 1, lastLine - 1);
    }
	
	/**
	 * Return lines
	 *
	 * @return Lines
	 */
	public Iterator<Line> lines() {
		return this.lines.iterator();
	}
	
	/**
	 * Returns an iterator of text lines starting from a specified line
	 * 
	 * @param firstLine first line of the text
	 * @return An iterator of lines
	 */
	public Iterator<Line> lines(int firstLine) throws InvalidLineNumberException, InvalidLineRangeException {
		return this.getLinesIterator(firstLine, this.lines.size());
	}
	
	/**
	 * @param firstLine first line of the text
	 * @param lastLine last line of the text
	 * @return An iterator of lines
	 * @throws aed.dataStructures.InvalidIndexRangeException
	 */
	public Iterator<Line> lines(int firstLine, int lastLine) throws InvalidLineNumberException, InvalidLineRangeException {
		return this.getLinesIterator(firstLine, lastLine);
	}

	/**
	 * Return number of lines
	 * @return number of lines
	 */
	public int numberOfLines(){
		return this.lines.size();
	}
}
