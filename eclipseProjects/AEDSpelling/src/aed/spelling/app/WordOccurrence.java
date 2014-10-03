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
		this.frequency = 0;
		this.isCorrect = dictionary.verifyWord(word);
		this.lineNumbers = new LinkedList<Integer>();
	}

	@Override
	public int getFrequency() {
		return this.frequency;
	}

	@Override
	public boolean isCorrect() {
		return this.isCorrect;
	}

	@Override
	public Iterator<Integer> linesNr() {
		return this.lineNumbers.iterator();
	}

	@Override
	public void incrementFrequency(int lineNumber) {
		this.frequency++;
		//TODO:
		//if(this.lineNumbers.getLast() != lineNumber) {
		//	this.lineNumbers.add(lineNumber);
		//}
		this.lineNumbers.add(lineNumber);
	}
}
