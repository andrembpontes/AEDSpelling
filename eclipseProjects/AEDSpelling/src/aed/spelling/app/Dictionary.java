package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.dataStructures.LinkedList;
import aed.dataStructures.List;
import aed.spelling.InvalidWordException;
import aed.spelling.Word;

public class Dictionary implements IDictionary {

	private List<Word> words;
	
	public Dictionary() {
		this.words = new LinkedList<Word>();
	}
	
	@Override
	public Word addWord(String word) throws InvalidWordException{
		if (this.verifyWord(word)) {
			return null;
		}
		Word newWord = new Word(word);
		this.words.add(newWord);
		return newWord;
	}

	@Override
	public boolean verifyWord(String word) {
		for(Iterator<Word> iterator = this.words.iterator(); iterator.hasNext();) {
			if (iterator.next().getWord().equals(word)){
				return true;
			}
		}
		return false;
	}
	
}	
