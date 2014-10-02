package aed.spelling;

import aed.dataStructures.Iterator;
import aed.spelling.app.IWordOccurrence;

public class WordOccurrence extends Word implements IWordOccurrence{


	
	public WordOccurrence(String word) throws InvalidWordException {
		super(word);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int frequency() {
		
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

}
