package aed.spelling.app;

import aed.dataStructures.Iterator;

/**
 * @author Andre Pontes (42845)
 */
public interface IWordOccurrence {
	/**
	 * Return times word appear on text
	 * 
	 * @return Times word appear on text
	 */
	int frequency();
	
	/**
	 * Return word
	 * 
	 * @return Word
	 */
	String getWord();
	
	/**
	 * Verify if word is in the dictionary
	 * 
	 * @return True if correct, False if not
	 */
	boolean isCorrect();
	
	/**
	 * Lines number where word is present
	 * 
	 * @return Iterator for lines number where word is present
	 */
	Iterator<Integer> linesNr();
}
