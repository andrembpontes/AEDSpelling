package aed.spelling.app;

import java.io.Serializable;

import aed.spelling.InvalidWordException;
import aed.spelling.Word;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public interface IDictionary extends Serializable {
	
	/**
	 * Adds a word to the dictionary
	 * 
	 * @param word Word to add
	 * @return The processed word
	 * @throws InvalidWordException
	 */
	Word addWord(String word) throws InvalidWordException;
	
	/**
	 * Verifies if a word exists in the dictionary
	 * 
	 * @param word Word to verify
	 */
	boolean verifyWord(String word);
	
}
