package aed.spelling;

import aed.spelling.app.IDictionaryWord;

/**
 * Represents a word. Its considered a word a char sequence without spaces
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 */
public class Word implements IDictionaryWord {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String				word;
	
	/**
	 * Create word and verify its validity
	 *
	 * @param word Word
	 * @throws InvalidWordException Givens word are not a valid word. Have a space char.
	 */
	public Word(String word) throws InvalidWordException {
		if (word.contains(" "))
			throw new InvalidWordException();
		
		this.word = word;
	}

	@Override
	public boolean equals(Word word) {
		return this.word.equals(word.getWord());
	}

	/**
	 * Return Word
	 *
	 * @return Word
	 */
	public String getWord() {
		return this.word;
	}
}
