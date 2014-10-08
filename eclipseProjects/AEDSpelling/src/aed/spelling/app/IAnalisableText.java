package aed.spelling.app;

import java.io.Serializable;

import aed.dataStructures.Iterator;
import aed.spelling.Line;

public interface IAnalisableText extends Serializable{
	/**
	 * Return text words that are not found in Dictionary
	 * 
	 * @return Text words that are not in Dictionary
	 */
	Iterator<IWordOccurrence> errors();
	
	/**
	 * Return text words that are found in Dictionary
	 * 
	 * @return Text word that are not in Dictionary
	 */
	Iterator<IWordOccurrence> correct();
	
	/**
	 * Calculate word frequency
	 * 
	 * @param word Word to calculate frequency
	 * @return Number of times word appears in text
	 */
	int frequency(String word);
	
	/**
	 * Return id
	 * 
	 * @return Id
	 */
	String getId();
	
	/**
	 * Iterator for text lines
	 * 
	 * @return Lines
	 */
	Iterator<Line> lines();
	
    /**
     * 
     * @param firstLine first line of the text
     * @param lastLine last line of the text
     * @return An iterator of lines
     */
    Iterator<Line> lines(int firstLine, int lastLine);
	
	/**
	 * Iterator for word occurrences in text
	 * 
	 * @return Occurrences
	 */
	Iterator<IWordOccurrence> occurrences();
}
