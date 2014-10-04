package aed.spelling;

import aed.dataStructures.Iterator;
import aed.dataStructures.LinkedList;
import aed.dataStructures.List;
import aed.spelling.app.AnalisableText;
import aed.spelling.app.Dictionary;
import aed.spelling.app.IAnalisableText;
import aed.spelling.app.IDictionary;
import aed.spelling.app.ISpelling;
import aed.spelling.app.IWordOccurrence;

public class Spelling implements ISpelling{
	
	private List<IAnalisableText> texts;
	private IDictionary dictionary;
	
	public Spelling() {
		this.texts = new LinkedList<IAnalisableText>();
		this.dictionary = new Dictionary();
	}
	
	@Override
	public boolean addText(String id, List<String> text) {
		if (this.searchText(id) != null) {
			return false;
		}
		this.texts.add(new AnalisableText(id, text, this.dictionary));
		return true;
	}

	@Override
	public boolean addWords(List<String> words) throws InvalidWordException{
		boolean anyAdded = false;		
		for (Iterator<String> iterator = words.iterator(); iterator.hasNext();) {
			String newWord = iterator.next();
			anyAdded = (anyAdded || (this.dictionary.addWord(newWord) != null));
		}
		
		if (anyAdded) {
			this.texts = new LinkedList<IAnalisableText>();
		}
		return anyAdded;
	}

	@Override
	public Text delText(String id) {
		IAnalisableText text = this.searchText(id);
		this.texts.del(text);
		return (Text)text;
	}

	@Override
	public int frequencyOf(String id, String word) {
		//TODO: return this.searchText(id).frequency(word)
		return 0;
	}

	@Override
	public Iterator<IWordOccurrence> textErrors(String id) {
		return this.searchText(id).errors();
	}

	@Override
	public Iterator<Line> textLines(String id) {
		returtn this.searchText(id).lines();
	}

	@Override
	public Iterator<Line> textLines(String id, int firstLine, int lastLine) {
		return this.searchText(id).lines(firstLine, lastLine);
	}

	@Override
	public boolean verifyWord(String word) {
		return this.dictionary.verifyWord(word);
	}

	@Override
	public Iterator<IWordOccurrence> wordsOf(String id) {
		return this.searchText(id).occurrences();
	}
	
	private IAnalisableText searchText(String id) {
		for (Iterator<IAnalisableText> iterator = this.texts.iterator(); iterator.hasNext();) {
			IAnalisableText text = iterator.next();
			if (text.getId().equals(id)) {
				return text;
			}
		}
		return null;
	}

}
