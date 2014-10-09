package aed.spelling;

import java.io.Serializable;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 *
 * Represents a word. Its considered a word a char sequence without spaces
 */
public class Word implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String word;

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

	/**
	 * Return Word
	 *
	 * @return Word
	 */
	public String getWord() {
		return this.word;
	}
}
