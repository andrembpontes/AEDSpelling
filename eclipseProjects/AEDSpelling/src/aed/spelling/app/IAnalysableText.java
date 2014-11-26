package aed.spelling.app;

import aed.dataStructures.InvalidIndexException;
import aed.dataStructures.InvalidIndexRangeException;
import aed.dataStructures.Iterator;
import aed.spelling.Line;

import java.io.Serializable;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public interface IAnalysableText extends Serializable {
	
	/**
	 * Return text words that are found in Dictionary with specified frequency
	 * 
	 * @return Text word that are not in Dictionary
	 */
	Iterator<IWordInText> correct(int frequency);
	
	/**
	 * Return text words that are not found in Dictionary
	 * 
	 * @return Text words that are not in Dictionary
	 */
	Iterator<IWordInText> errors();

	/**
	 * Return text words that are not found in Dictionary with specified frequency
	 *
	 * @param frequency frequency
	 * @return Text words that are not in Dictionary
	 */
	Iterator<IWordInText> errors(int frequency);
	
	/**
	 * Calculate word frequency
	 * 
	 * @param word Word to calculate frequency
	 * @return Number of times word appears in text
	 */
	int frequency(String word);
	
	/**
	 * Return id
	 * 
	 * @return Id
	 */
	String getId();
	
	/**
	 * Iterator for text lines
	 * 
	 * @return Lines
	 */
	Iterator<Line> lines();
	
	/**
	 * @param firstLine first line of the text
	 * @return An iterator of lines
	 */
	Iterator<Line> lines(int firstLine) throws InvalidIndexException;
	
	/**
	 * @param firstLine first line of the text
	 * @param lastLine last line of the text
	 * @return An iterator of lines
	 */
	Iterator<Line> lines(int firstLine, int lastLine) throws InvalidIndexException, InvalidIndexRangeException;
	
	/**
	 * Iterator for word occurrences in text with specified frequency
	 *
	 * @param frequency frequency
	 * @return Occurrences
	 */
	Iterator<IWordInText> occurrences(int frequency);
}
