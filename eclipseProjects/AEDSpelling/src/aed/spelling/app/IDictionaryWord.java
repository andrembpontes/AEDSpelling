package aed.spelling.app;

import java.io.Serializable;

import aed.spelling.Word;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 */
public interface IDictionaryWord extends Serializable {
	
	/**
	 * Returns true if this is equal to the specified word, false if not
	 * 
	 * @param word Word to check
	 * @return True if equals, else False
	 */
	boolean equals(Word word);
	
	/**
	 * Returns the string value of the word
	 * 
	 * @return the string value of the word
	 */
	String getWord();
}
