package aed.spelling.app;

import aed.dataStructures.HashMap;
import aed.dataStructures.Map;
import aed.spelling.InvalidWordException;
import aed.spelling.Word;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 */
class Dictionary implements IDictionary {
	
	private static final long serialVersionUID = 1L;
	private Map<String, IDictionaryWord> words;
	
	public Dictionary(int startSize) {
		this.words = new HashMap<String, IDictionaryWord>(startSize);
	}

	public Dictionary(){
		this(IDictionary.START_SIZE);
	}

	@Override
	public Word addWord(String word) throws InvalidWordException {
		String lowerWord = word.toLowerCase();
        if (this.verifyWord(lowerWord))
            return null;

        Word newWord = new Word(lowerWord);
        this.words.put(lowerWord, newWord);
        return newWord;
	}
	
	@Override
	public boolean verifyWord(String word) {
		return words.containsKey(word.toLowerCase());
	}
	
}
