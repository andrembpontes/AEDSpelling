package aed.spelling.app;

import java.io.Serializable;

import aed.dataStructures.Iterator;
import aed.spelling.InvalidLineNumberException;
import aed.spelling.InvalidLineRangeException;
import aed.spelling.Line;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public interface IAnalisableText extends Serializable {
	
	/**
	 * Return text words that are found in Dictionary
	 * 
	 * @return Text word that are not in Dictionary
	 */
	Iterator<IWordInText> correct();
	
	/**
	 * Return text words that are not found in Dictionary
	 * 
	 * @return Text words that are not in Dictionary
	 */
	Iterator<IWordInText> errors();
	
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
	Iterator<Line> lines(int firstLine) throws InvalidLineNumberException;
	
	/**
	 * @param firstLine first line of the text
	 * @param lastLine last line of the text
	 * @return An iterator of lines
	 */
	Iterator<Line> lines(int firstLine, int lastLine) throws InvalidLineNumberException, InvalidLineRangeException;
	
	/**
	 * Iterator for word occurrences in text
	 * 
	 * @return Occurrences
	 */
	Iterator<IWordInText> occurrences();
}
