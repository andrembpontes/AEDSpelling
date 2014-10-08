package aed.spelling.app;

import java.io.Serializable;

import aed.spelling.Word;

public interface IDictionaryWord extends Serializable{
	
	/**
	 * Returns true if this is equal to the specified word, false if not
	 * 
	 * @param word Word to check
	 */
	boolean equals(Word word);
	
	/**
	 * Returns the string value of the word
	 * 
	 * @return the string value of the word
	 */
	String getWord();
}
