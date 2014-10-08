package aed.spelling.app;

import java.io.Serializable;

import aed.spelling.InvalidWordException;
import aed.spelling.Word;

public interface IDictionary extends Serializable{

	/**
	 * Adds a word to the dictionary
	 * 
	 * @param word Word to add 
	 * @return The processed word
	 * @throws InvalidWordException 
	 */
	Word addWord(String word) throws InvalidWordException ;
	
	/**
	 * Verifies if a word exists in the dictionary
	 * 
	 * @param word Word to verify
	 */
	boolean verifyWord(String word);
	
	
}
