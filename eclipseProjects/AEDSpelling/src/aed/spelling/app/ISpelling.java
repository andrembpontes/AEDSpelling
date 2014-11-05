package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.dataStructures.List;
import aed.spelling.InvalidLineNumberException;
import aed.spelling.InvalidLineRangeException;
import aed.spelling.InvalidWordException;
import aed.spelling.Line;

import java.io.Serializable;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public interface ISpelling extends Serializable {
	
	/**
	 * Create a new text with the given ID
	 * 
	 * @param id Id
	 * @param text Text to add
	 * @return True if the text was added, false if the id is already in use
	 */
	boolean addText(String id, List<String> text);
	
	/**
	 * Add a list word to the dictionary if the words weren't already there
	 * 
	 * @param words Words to add
	 * @return True if any word was added false if not
	 * @throws InvalidWordException
	 */
	boolean addWords(List<String> words) throws InvalidWordException;
	
	/**
	 * Delete the text with the given ID
	 * 
	 * @param id Id
	 * @return True if there is no text with the given ID else returns False
	 */
	boolean delText(String id);
	
	/**
	 * Returns the frequency of a given word on a text
	 * 
	 * @param id Id
	 * @param word Word to get the frequency of
	 * @return The frequency of a given word on a text
	 */
	int frequencyOf(String id, String word);
	
	/**
	 * Returns an iterator of correct words off a given text
	 * 
	 * @param id Id
	 * @return An iterator of correct words off a given text
	 */
	Iterator<IWordInText> textCorrects(String id);
	
	/**
	 * Returns an iterator of wrong words off a given text
	 * 
	 * @param id Id
	 * @return An iterator of wrong words off a given text
	 */
	Iterator<IWordInText> textErrors(String id);
	
	/**
	 * Returns an iterator of lines off a given text
	 * 
	 * @param id Id
	 * @return An iterator of lines off a given text
	 */
	Iterator<Line> textLines(String id);
	
	/**
	 * Returns an iterator of lines off a given text enumerated between boundaries
	 * 
	 * @param id Id
	 * @param firstLine First included line
	 * @param lastLine Last included line
	 * @return An iterator of lines off a given text enumerated between boundaries
	 */
	Iterator<Line> textLines(String id, int firstLine, int lastLine) throws InvalidLineNumberException, InvalidLineRangeException;
	
	/**
	 * Verifies if a word exists in the dictionary
	 *
	 * @param word Word to search
	 * @return True if than iterator of lines off a given text enumerated between boundariese word exists in the dictionary, false if not
	 */
	boolean verifyWord(String word);
	
	/**
	 * Returns an iterator of words off a given text
	 * 
	 * @param id Id
	 * @return An iterator of words off a given text
	 */
	Iterator<IWordInText> wordsOf(String id);
}
