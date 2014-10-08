package aed.spelling.app;

import java.io.Serializable;

import aed.dataStructures.Iterator;

public interface IAnalysisResults extends Serializable{
	
	/**
	 * Returns an iterator for the errors found
	 * 
	 * @return An iterator for the errors found
	 */
	Iterator<IWordOccurrence> errors();
	
	/**
	 * Returns the frequency of a word
	 * 
	 * @param word Word to check the frequency for
	 * @return The frequency of a word
	 */
	int frequency(String word);
	
	/**
	 * Returns an iterator of occurrences of a word
	 * 
	 * @return An iterator of occurrences of a word
	 */
	Iterator<IWordOccurrence> occurrences();

	/**
	 * Returns an iterator for the correct found
	 * 
	 * @return An iterator for the correct found
	 */
	Iterator<IWordOccurrence> correct();	
}

