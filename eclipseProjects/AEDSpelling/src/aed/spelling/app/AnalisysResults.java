package aed.spelling.app;

import aed.dataStructures.Collection;
import aed.dataStructures.HashMap;
import aed.dataStructures.Iterator;
import aed.dataStructures.Map;
import aed.dataStructures.tree.BinarySearchTree;
import aed.spelling.Line;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
class AnalisysResults implements IAnalysisResults {
	
	private static final long	serialVersionUID	= 1L;
	private IAnalisableText		analisableText;
	private IDictionary			dictionary;
	private Map<String, IWordOccurrence> errors, corrects,  occurrences, orderedOccurrences;
	
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
            String key = word.toLowerCase();

			occurrence = new WordOccurrence(key, this.dictionary);

			this.occurrences.put(key, occurrence);
            this.orderedOccurrences.put(key, occurrence);
			
			if (occurrence.isCorrect())
				this.corrects.put(key, occurrence);
			else
				this.errors.put(key, occurrence);
		}
		occurrence.incrementFrequency(lineNumber);
	}
	
	/**
	 * Analyzes a text
	 */
	private void analise() {
		this.occurrences = new HashMap<String, IWordOccurrence>();
        this.orderedOccurrences = new BinarySearchTree<String, IWordOccurrence>();
		this.errors = new BinarySearchTree<String, IWordOccurrence>();
		this.corrects = new BinarySearchTree<String, IWordOccurrence>();
		
		Iterator<Line> lines = this.analisableText.lines();
		
		while (lines.hasNext()) {
			Line line = lines.next();
			
			String[] words = line.getLine().split("\\W+");
			
			for (String word : words)
				if (!word.isEmpty())
					this.addOccurrence(word.toLowerCase(), line.getNr());
		}
	}
	
	@Override
    @SuppressWarnings("unchecked")
	public Iterator<IWordInText> correct() {
		return  ((Collection<IWordInText>)(Collection<? extends IWordInText>)this.corrects.values()).iterator();
	}
	
	@Override
    @SuppressWarnings("unchecked")
	public Iterator<IWordInText> errors() {
		return ((Collection<IWordInText>)(Collection<? extends IWordInText>)this.errors.values()).iterator();
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
		return this.occurrences.get(word.toLowerCase());
	}
	
	@Override
    @SuppressWarnings("unchecked")
	public Iterator<IWordInText> occurrences() {
		return ((Collection<IWordInText>)(Collection<? extends IWordInText>)this.orderedOccurrences.values()).iterator();
	}
	
}
