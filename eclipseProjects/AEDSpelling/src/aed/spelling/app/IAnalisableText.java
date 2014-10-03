package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.spelling.Line;
import aed.spelling.Word;

public interface IAnalisableText {
	/**
	 * Return text words that are not found in Dictionary
	 * 
	 * @return Text words that are not in Dictionary
	 */
	Iterator<IWordOccurrence> errors();
	
	/**
	 * Calculate word frequency
	 * 
	 * @param word Word to calculate frequency
	 * @return Number of times word appears in text
	 */
	int frequency(Word word);
	
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
	 * Iterator for word occurrences in text
	 * 
	 * @return Occurrences
	 */
	Iterator<IWordOccurrence> occurrences();
}