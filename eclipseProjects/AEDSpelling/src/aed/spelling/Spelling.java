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
		this.texts.addLast(new AnalisableText(id, text, this.dictionary));
		return true;
	}

	@Override
	public boolean addWords(List<String> words) throws InvalidWordException{
		boolean anyAdded = false;		
		for (Iterator<String> iterator = words.iterator(); iterator.hasNext();) {
			String newWord = iterator.next();
			anyAdded = ((this.dictionary.addWord(newWord) != null) || anyAdded);
		}
		
		if (anyAdded) {
			this.texts = new LinkedList<IAnalisableText>();
		}
		return anyAdded;
	}

	@Override
	public boolean delText(String id) {
		//TODO: Optimizar?
		IAnalisableText text = this.searchText(id);
		if(text != null) {
			this.texts.remove(text);
			return true;
		}
		return false;
	}

	@Override
	public int frequencyOf(String id, String word) {
		//TODO: return this.searchText(id).frequency(word)
		return 0;
	}

	@Override
	public Iterator<IWordOccurrence> textErrors(String id) {
		IAnalisableText text = this.searchText(id);
		return (text != null) ? text.errors() : null;
	}

	@Override
	public Iterator<Line> textLines(String id) {
		IAnalisableText text = this.searchText(id);
		return (text != null) ? text.lines() : null;
	}

	@Override
	public Iterator<Line> textLines(String id, int firstLine, int lastLine) {
		IAnalisableText text = this.searchText(id);
		return (text != null) ? text.lines(firstLine, lastLine) : null;
	}

	@Override
	public boolean verifyWord(String word) {
		return this.dictionary.verifyWord(word);
	}

	@Override
	public Iterator<IWordOccurrence> wordsOf(String id) {
		IAnalisableText text = this.searchText(id);
		return (text != null) ? text.occurrences() : null;
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
