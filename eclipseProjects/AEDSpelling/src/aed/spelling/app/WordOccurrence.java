package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.dataStructures.LinkedList;
import aed.dataStructures.List;
import aed.spelling.InvalidWordException;
import aed.spelling.Word;

public class WordOccurrence extends Word implements IWordOccurrence{

	private int frequency;
	private boolean isCorrect;
	private List<Integer> lineNumbers;

	public WordOccurrence(String word, IDictionary dictionary) throws InvalidWordException {
		super(word);
		this.isCorrect = dictionary.verifyWord(word);
		this.lineNumbers = new LinkedList<Integer>();
	}

	@Override
	public int getFrequency() {
		return frequency;
	}

	@Override
	public boolean isCorrect() {
		return isCorrect;
	}

	@Override
	public Iterator<Integer> linesNr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void incrementFrequency(int lineNumber) {
		this.frequency++;
		this.lineNumbers.add(lineNumber);
	}
}
