package aed.spelling.app;

import aed.dataStructures.List;
import aed.dataStructures.Iterator;
import aed.spelling.*;


public interface ISpelling {
	
	/**
	 * Create a new text with the given ID
	 * 
	 * @param id Id
	 * @param text Text to add
	 */
	void addText(String id, List<String> text);
	
	
	/**
	 * Add a list word to the dictionary if the words werent already there
	 * 
	 * @param words Words to add
	 * @return An iterator of the words that were added
	 */
	Iterator<Word> addWords(List<String> words);
	
	/**
	 * Delete the text with the given ID
	 * 
	 * @param id Id
	 * @return Null if there is no text with the given ID else returns the deleted text
	 */
	Text delText(String id);
	
	/**
	 * Returns the frequency of a given word on a text
	 * 
	 * @param id Id
	 * @param word Word to get the frequency of
	 * @return The frequency of a given word on a text
	 */
	int frequencyOf(String id, String word);
	
	/**
	 * Returns an iterator of word occurrences off a given text
	 * 
	 * @param id Id
	 * @return An iterator of word occurrences off a given text
	 */
	Iterator<IWordOccurrence> textErrors(String id);

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
	Iterator<Line> textLines(String id, int firstLine, int lastLine);
	
	/**
	 * Verifies if a word exists in the dictionary 
	 *
	 * @param word Word to search
	 * @return True if than iterator of lines off a given text enumerated between boundariese word exists in the dictionary, false if not
	 */
	boolean verifyWord(String word);
	
	
	/**
	 * Returns an iterator of lines off a given text
	 * 
	 * @param id Id
	 * @return An iterator of lines off a given text
	 */
	Iterator<IWordOccurrence> wordsOf(String id);
}
