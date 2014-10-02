package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.dataStructures.LinkedList;
import aed.dataStructures.List;
import aed.spelling.InvalidWordException;
import aed.spelling.Line;
import aed.spelling.Word;

public class AnalisysResults implements IAnalysisResults {

	private IAnalisableText analisableText;
	private IDictionary dictionary;
	private List<IWordOccurrence> errors, occurrences;
	
	public AnalisysResults(IAnalisableText analisableText, IDictionary dictionary) {
		this.analisableText = analisableText;
		this.dictionary = dictionary;
		
		this.analise();
	}
	
	private void analise(){
		this.occurrences = new LinkedList<IWordOccurrence>();
		this.errors = new LinkedList<IWordOccurrence>();
		
		Iterator<Line> lines = this.analisableText.lines();
		
		for(Line line = lines.next(); lines.hasNext(); line = lines.next()){
			String[] words = line.getLine().split(" ");
			
			for(String word : words){
				this.addOccurrence(word);
			}
		}
		
	}
	
	private void addOccurrence(String word){
		IWordOccurrence occurrence = this.getWordOccurrence(word);
		
		if(occurrence == null){
			try {
				occurrence = new WordOccurrence(word, this.dictionary);
			} catch (InvalidWordException e) {
				e.printStackTrace();
			}
		
			this.occurrences.add(occurrence);
			if(!occurrence.isCorrect())
				this.errors.add(occurrence);
		}
		else{
			occurrence.incrementFrequency();
		}
	}
	
	private IWordOccurrence getWordOccurrence(String word){
		for(IWordOccurrence occurrence : this.occurrences){
			if(occurrence.getWord().equals(word))
				return occurrence;
		}
		
		return null;
	}

	@Override
	public Iterator<IWordOccurrence> errors() {
		return this.errors.iterator();
	}

	@Override
	public int frequency(Word word) {
		for(IWordOccurrence occurrence : this.occurrences)
			if(word.equals(occurrence))
				return occurrence.getFrequency();
		
		return 0;
	}

	@Override
	public Iterator<IWordOccurrence> occurrences() {
		return this.occurrences.iterator();
	}

}
