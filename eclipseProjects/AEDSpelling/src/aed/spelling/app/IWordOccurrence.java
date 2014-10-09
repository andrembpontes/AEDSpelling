package aed.spelling.app;

import java.io.Serializable;

import aed.dataStructures.Iterator;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public interface IWordOccurrence extends Serializable {
	
	/**
	 * Return times word appear on text
	 * 
	 * @return Times word appear on text
	 */
	int getFrequency();
	
	/**
	 * Return word
	 * 
	 * @return Word
	 */
	String getWord();
	
	/**
	 * Increments the word frequency
	 */
	void incrementFrequency(int lineNumber);
	
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
