package aed.spelling.app;


/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 */
public interface IWordOccurrence extends IWordInText {

	/**
	 * Increments the word frequency
	 *
	 * @param lineNumber line where are frequency
	 */
	void incrementFrequency(int lineNumber);

}
