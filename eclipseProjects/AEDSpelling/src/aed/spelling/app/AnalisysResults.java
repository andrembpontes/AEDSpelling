package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.dataStructures.LinkedList;
import aed.dataStructures.List;
import aed.spelling.Line;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public class AnalisysResults implements IAnalysisResults {
	
	private static final long	serialVersionUID	= 1L;
	private IAnalisableText		analisableText;
	private IDictionary			dictionary;
	private List<IWordOccurrence>	errors, corrects, occurrences;
	
	public AnalisysResults(IAnalisableText analisableText, IDictionary dictionary) {
		this.analisableText = analisableText;
		this.dictionary = dictionary;
		
		this.analise();
	}
	
	/**
	 * Adds an occurrence of a word
	 * @param word Word to add 
	 * @param lineNumber Line number of the occurence
	 */
	private void addOccurrence(String word, int lineNumber) {
		IWordOccurrence occurrence = this.getWordOccurrence(word);
		
		if (occurrence == null) {
			occurrence = new WordOccurrence(word, this.dictionary);
			
			this.occurrences.addLast(occurrence);
			
			if (occurrence.isCorrect())
				this.corrects.addLast(occurrence);
			else
				this.errors.addLast(occurrence);
		}
		occurrence.incrementFrequency(lineNumber);
	}
	
	/**
	 * Analyzes a text
	 */
	private void analise() {
		this.occurrences = new LinkedList<IWordOccurrence>();
		this.errors = new LinkedList<IWordOccurrence>();
		this.corrects = new LinkedList<IWordOccurrence>();
		
		Iterator<Line> lines = this.analisableText.lines();
		
		while (lines.hasNext()) {
			Line line = lines.next();
			
			String[] words = line.getLine().split("\\s+");
			
			for (String word : words)
				if (!word.isEmpty())
					this.addOccurrence(word, line.getNr());
		}
	}
	
	@Override
	public Iterator<? extends IWordInText> correct() {
		return this.corrects.iterator();
	}
	
	@Override
	public Iterator<? extends IWordInText> errors() {
		return this.errors.iterator();
	}
	
	@Override
	public int frequency(String word) {
		IWordOccurrence wordOccurrence = this.getWordOccurrence(word);
		if (wordOccurrence != null)
			return wordOccurrence.getFrequency();
		return 0;
	}
	
	/**
	 * Returns an iterator of the occurrences of a specified word
	 * @param word Word to search for
	 * @return An iterator of the occurrences of a specified word
	 */
	private IWordOccurrence getWordOccurrence(String word) {
		for (Iterator<IWordOccurrence> iterator = this.occurrences.iterator(); iterator.hasNext();) {
			IWordOccurrence occurrence = iterator.next();
			if (occurrence.getWord().equalsIgnoreCase(word))
				return occurrence;
		}

		return null;
	}
	
	@Override
	public Iterator<? extends IWordInText> occurrences() {
		return this.occurrences.iterator();
	}
	
}
