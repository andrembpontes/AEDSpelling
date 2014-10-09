package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.dataStructures.LinkedList;
import aed.dataStructures.List;
import aed.spelling.InvalidWordException;
import aed.spelling.Word;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 *
 */
public class Dictionary implements IDictionary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Word> words;

	public Dictionary() {
		this.words = new LinkedList<Word>();
	}

	@Override
	public Word addWord(String word) throws InvalidWordException {
		if (this.verifyWord(word))
			return null;
		Word newWord = new Word(word);
		this.words.addLast(newWord);
		return newWord;
	}

	@Override
	public boolean verifyWord(String word) {
		for (Iterator<Word> iterator = this.words.iterator(); iterator
				.hasNext();) {
			String dictionaryWord = iterator.next().getWord();
			if (dictionaryWord.equalsIgnoreCase(word))
				return true;
		}
		return false;
	}

}
