package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.dataStructures.LinkedList;
import aed.dataStructures.List;
import aed.spelling.InvalidWordException;
import aed.spelling.Line;

public class AnalisysResults implements IAnalysisResults {

	private IAnalisableText analisableText;
	private IDictionary dictionary;
	private List<IWordOccurrence> errors, corrects, occurrences;
	
	public AnalisysResults(IAnalisableText analisableText, IDictionary dictionary) {
		this.analisableText = analisableText;
		this.dictionary = dictionary;
		
		this.analise();
	}
	
	private void analise(){
		this.occurrences = new LinkedList<IWordOccurrence>();
		this.errors = new LinkedList<IWordOccurrence>();
		this.corrects = new LinkedList<IWordOccurrence>();
		
		Iterator<Line> lines = this.analisableText.lines();
		
		while(lines.hasNext()){
			Line line = lines.next();
			
			String[] words = line.getLine().split("\\s+");
			
			for(String word : words){
				this.addOccurrence(word, line.getNr());
			}
		}
	}
	
	private void addOccurrence(String word, int lineNumber){
		IWordOccurrence occurrence = this.getWordOccurrence(word);
		
		if(occurrence == null){
			//TODO: qual é o objectivo disto ?
			try {
				occurrence = new WordOccurrence(word, this.dictionary);
			} catch (InvalidWordException e) {
				e.printStackTrace();
			}
		
			this.occurrences.addLast(occurrence);
			
			if(occurrence.isCorrect())
				this.corrects.addLast(occurrence);
			else
				this.errors.addLast(occurrence);
		}
		occurrence.incrementFrequency(lineNumber);
	}
	
	private IWordOccurrence getWordOccurrence(String word){
		for(Iterator<IWordOccurrence> iterator = this.occurrences.iterator(); iterator.hasNext();){
			IWordOccurrence occurrence = iterator.next();
			if(occurrence.getWord().equalsIgnoreCase(word)) {
				return occurrence;
			}
		}
		
		return null;
	}

	@Override
	public Iterator<IWordOccurrence> errors() {
		return this.errors.iterator();
	}

	@Override
	public int frequency(String word) {
		IWordOccurrence wordOccurrence = this.getWordOccurrence(word);
		if (wordOccurrence != null) {
			return wordOccurrence.getFrequency();
		}
		return 0;
	}

	@Override
	public Iterator<IWordOccurrence> occurrences() {
		return this.occurrences.iterator();
	}

	@Override
	public Iterator<IWordOccurrence> correct() {
		return this.corrects.iterator();
	}

}
