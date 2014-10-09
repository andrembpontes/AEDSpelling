package aed.spelling;

import java.io.Serializable;

import aed.dataStructures.ArrayIterator;
import aed.dataStructures.ArrayList;
import aed.dataStructures.Iterator;
import aed.dataStructures.List;

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
	 * @param lines Lines list
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
	private Iterator<Line> getLinesIterator(int firstLine, int lastLine) {
		
		Line[] selectedLines = new Line[lastLine - firstLine + 1];
		
		int count = 0;
		for (int i = firstLine; i <= lastLine; i++)
			selectedLines[count++] = this.lines.get(i);
		
		return new ArrayIterator<Line>(selectedLines, count);
		
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
	 * @throws InvalidLineRangeException
	 */
	public Iterator<Line> lines(int firstLine) throws InvalidLineNumberException {
		int firstIndex = firstLine - 1;
		
		if (firstIndex < 0)
			throw new InvalidLineNumberException();
		
		return this.getLinesIterator(firstIndex, this.lines.size() - 1);
	}
	
	/**
	 * @param firstLine first line of the text
	 * @param lastLine last line of the text
	 * @return An iterator of lines
	 * @throws InvalidLineRangeException
	 */
	public Iterator<Line> lines(int firstLine, int lastLine) throws InvalidLineNumberException, InvalidLineRangeException {
		int firstIndex = firstLine - 1;
		int lastIndex = lastLine - 1;
		
		if (!(firstIndex >= 0 && lastIndex < this.lines.size()))
			throw new InvalidLineNumberException();
		
		if (lastIndex - firstIndex < 0)
			throw new InvalidLineRangeException();
		
		return this.getLinesIterator(firstIndex, lastIndex);
	}
}
