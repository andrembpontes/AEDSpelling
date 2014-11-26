package aed.spelling.app;

import java.io.Serializable;

import aed.dataStructures.Iterator;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public interface IAnalysisResults extends Serializable {
	
	/**
	 * Returns an iterator for the correct words with specified frequency
	 *
	 * @param frequency frequency
	 * @return An iterator for the correct found
	 */
	Iterator<IWordInText> correct(int frequency);
	
	/**
	 * Returns an iterator for the wrong words with specified frequency
	 *
	 * @param frequency frequency
	 * @return An iterator for the errors found
	 */
	Iterator<IWordInText> errors(int frequency);

	/**
	 * Returns an iterator for the wrong words with specified frequency
	 *
	 * @return An iterator for the errors found
	 */
	Iterator<IWordInText> errors();

	/**
	 * Returns the frequency of a word
	 * 
	 * @param word Word to check the frequency for
	 * @return The frequency of a word
	 */
	int frequency(String word);
	
	/**
	 * Returns an iterator of occurrences of words with specified frequency
	 *
	 * @param frequency frequency
	 * @return An iterator of occurrences of a word
	 */
	Iterator<IWordInText> occurrences(int frequency);
}
