package aed.spelling.app;

import aed.dataStructures.List;
import aed.dataStructures.Iterator;
import aed.spelling.*;

public interface ISpelling {
	
	/**
	 * Create a new text with the given ID
	 * 
	 * @param id id
	 * @param text text to add
	 */
	void addText(String id, List<String> text);
	
	
	/**
	 * Add a list word to the dictionary if the words werent already there
	 * 
	 * @param words words to add
	 * @return an iterator of the words that were added
	 */
	Iterator<Word> addWords(List<String> words);
	
	/**
	 * Delete the text with the given ID
	 * 
	 * @param id id
	 * @return null if there is no text with the given ID else returns the deleted text
	 */
	Text delText(String id);
	
	/**
	 * Returns the word
	 * 
	 * @param id
	 * @param word
	 * @return
	 */
	int frequencyOf(String id, String word);
	
	/**
	 * @param id
	 * @return
	 */
	Iterator<IWordOccurrence> getTextErrors(String id);

	Iterator<Line> getTextLines(String id);
	
	Iterator<Line> getTextLines(String id, int firstLine, int lastLine);
	
	boolean verifyWord(String word);
	
	Iterator<IWordOccurrence> wordsOf(String id);
}
