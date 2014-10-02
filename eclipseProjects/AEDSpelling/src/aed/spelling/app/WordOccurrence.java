package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.spelling.InvalidWordException;
import aed.spelling.Word;

public class WordOccurrence extends Word implements IWordOccurrence{


	
	public WordOccurrence(String word) throws InvalidWordException {
		super(word);
		// TODO Auto-generated constructor stub
	}

	public WordOccurrence(String word, IDictionary dictionary) throws InvalidWordException {
		super(word);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getFrequency() {
		
		return 0;
	}

	@Override
	public boolean isCorrect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Integer> linesNr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void incrementFrequency() {
		// TODO Auto-generated method stub
		
	}

}
