package aed.spelling.app;


/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public interface IWordOccurrence extends IWordInText {

	/**
	 * Increments the word frequency
	 */
	void incrementFrequency(int lineNumber);

}
