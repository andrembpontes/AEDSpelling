package aed.spelling.app;

import aed.dataStructures.InsertionList;
import aed.dataStructures.Iterator;
import aed.dataStructures.LinkedList;
import aed.spelling.InvalidWordException;
import aed.spelling.Word;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public class WordOccurrence extends Word implements IWordOccurrence {
	
	private static final long	serialVersionUID	= 1L;
	private int					frequency;
	private boolean				isCorrect;
	private InsertionList<Integer> lineNumbers;
	
	public WordOccurrence(String word, IDictionary dictionary) throws InvalidWordException {
		super(word);
		this.frequency = 0;
		this.isCorrect = dictionary.verifyWord(word);
		this.lineNumbers = new LinkedList<Integer>();
	}
	
	@Override
	public int getFrequency() {
		return this.frequency;
	}
	
	@Override
	public void incrementFrequency(int lineNumber) {
		this.frequency++;
		if (this.lineNumbers.find(lineNumber) == -1)
			this.lineNumbers.addLast(lineNumber);
	}
	
	@Override
	public boolean isCorrect() {
		return this.isCorrect;
	}
	
	@Override
	public Iterator<Integer> linesNr() {
		return this.lineNumbers.iterator();
	}
}
