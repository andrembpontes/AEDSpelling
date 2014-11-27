package aed.spelling.app;

import aed.dataStructures.*;
import aed.spelling.InvalidWordException;
import aed.spelling.Line;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 */
public class Spelling implements ISpelling {
	
	private static final long serialVersionUID = 1L;
	
	private Map<String, IAnalysableText> texts;
	private IDictionary dictionary;
	
	public Spelling() {
		this.texts = new HashMap<String, IAnalysableText>();
		this.dictionary = new Dictionary();
	}
	
	@Override
	public boolean addText(String id, List<String> text) {
		if (this.texts.containsKey(id))
			return false;

		this.texts.put(id, new AnalysableText(id, text, this.dictionary));
		return true;
	}
	
	@Override
	public boolean addWords(List<String> words) throws InvalidWordException {
		boolean anyAdded = false;
		for (Iterator<String> iterator = words.iterator(); iterator.hasNext();) {
			String newWord = iterator.next();
			anyAdded = this.dictionary.addWord(newWord) != null || anyAdded;
		}
		
		if (anyAdded)
			this.texts = new HashMap<String, IAnalysableText>();

        return anyAdded;
	}
	
	@Override
	public boolean delText(String id) {
        return (this.texts.remove(id) != null);

	}
	
	@Override
	public int frequencyOf(String id, String word) {
		IAnalysableText text = this.searchText(id);
		if (text != null)
			return text.frequency(word);
		return -1;
	}
	
	/**
	 * Returns the text with the specified id, null if the text is not found 
	 * 
	 * @param id Id to search for
	 * @return The text with the specified id, null if the text is not found 
	 */
	private IAnalysableText searchText(String id) {
        return this.texts.get(id);
	}
	
	@Override
	public Iterator<IWordInText> textCorrects(String id, int frequency) {
		IAnalysableText text = this.searchText(id);
		if (text == null)
			throw new TextNotFoundException();
		
		return text.correct(frequency);
	}
	
	@Override
	public Iterator<IWordInText> textErrors(String id) {
		IAnalysableText text = this.searchText(id);
		if (text == null)
			throw new TextNotFoundException();
		
		return text.errors();
	}

	@Override
	public Iterator<IWordInText> textErrors(String id, int frequency) {
		IAnalysableText text = this.searchText(id);
		if (text == null)
			throw new TextNotFoundException();

		return text.errors(frequency);
	}

	@Override
	public Iterator<IWordInText> wordsWithFrequency(String id, int frequency) {
		IAnalysableText text = this.searchText(id);

		if (text == null)
			throw new TextNotFoundException();

		return text.occurrences(frequency);
	}

	@Override
	public Iterator<Line> textLines(String id) {
		IAnalysableText text = this.searchText(id);
		
		if (text == null)
			throw new TextNotFoundException();
		
		return text.lines();
	}
	
	@Override
	public Iterator<Line> textLines(String id, int firstLine, int lastLine) throws InvalidIndexException, InvalidIndexRangeException {
		IAnalysableText text = this.searchText(id);
		if (text == null)
			throw new TextNotFoundException();
		
		if (lastLine == 0)
			return text.lines(firstLine);
		
		return text.lines(firstLine, lastLine);
	}
	
	@Override
	public boolean verifyWord(String word) {
		return this.dictionary.verifyWord(word);
	}
	
}
