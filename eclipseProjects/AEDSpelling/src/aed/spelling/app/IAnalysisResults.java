package aed.spelling.app;

import aed.dataStructures.Iterator;

public interface IAnalysisResults {
	
	/**
	 * Returns an iterator the errors found
	 * 
	 * @return An iterator the errors found
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
}

