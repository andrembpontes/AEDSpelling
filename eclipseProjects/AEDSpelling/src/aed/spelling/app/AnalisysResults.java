package aed.spelling.app;

import aed.dataStructures.*;
import aed.dataStructures.tree.AVLTree;
import aed.spelling.Line;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
class AnalisysResults implements IAnalysisResults {

	public static int AVERRAGE_TEXT_WORDS_N = 100;

	class AnalisysData {
		Map<String, IWordOccurrence> wordMap;
		Map<String, IWordOccurrence> errorTree;
		List<IWordOccurrence> correctList;

		AnalisysData(){
			wordMap = new HashMap<String, IWordOccurrence>();
			errorTree = new AVLTree<String, IWordOccurrence>();
			correctList = new LinkedList<IWordOccurrence>();
		}

		IWordOccurrence getWord(String word){
			return this.wordMap.get(word);
		}

		void addWord(String word, int lineNr){
			String key = word;
			IWordOccurrence occurrence = new WordOccurrence(word, dictionary);
			occurrence.incrementFrequency(lineNr);

			this.wordMap.put(key, occurrence);

			if(occurrence.isCorrect())
				correctList.add(occurrence);
			else
				errorTree.put(key, occurrence);
		}
	}

	private static final long	serialVersionUID	= 1L;
	private IAnalisableText		analisableText;
	private IDictionary			dictionary;

	//Index Occurrences by WordString
	private Map<String, IWordInText> wordMap;

	//All errors sort alphabetical
	private Map<String, IWordInText> errorTree;

	//Index occurrences by frequency and by type (Error, Correct or both)
	private Map<Integer, Map<String, IWordInText>> frequenciesErrorMap;
	private Map<Integer, Map<String, IWordInText>> frequenciesCorrectMap;
	private Map<Integer, Map<String, IWordInText>> frequenciesWordMap;
	
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
	private void addOccurrence(String word, int lineNumber, AnalisysData data) {
		IWordOccurrence occurrence = data.getWord(word);

		if (occurrence == null)
			data.addWord(word, lineNumber);
		else
			occurrence.incrementFrequency(lineNumber);
	}
	
	/**
	 * Analyzes a text
	 */
	private void analise() {
		AnalisysData data = new AnalisysData();
		
		Iterator<Line> lines = this.analisableText.lines();
		
		while (lines.hasNext()) {
			Line line = lines.next();
			
			String[] words = line.getLine().split("\\W+");
			
			for (String word : words)
				if (!word.isEmpty())
					this.addOccurrence(word.toLowerCase(), line.getNr(), data);
		}

		this.storeAnaliseData(data);
	}

	private void storeAnaliseData(AnalisysData data){
		this.wordMap = (Map<String, IWordInText>) (Map<String, ? extends IWordInText>) data.wordMap;
		this.errorTree = (Map<String, IWordInText>) (Map<String, ? extends IWordInText>) data.errorTree;
		this.indexWordByFrequency(data);
	}

	private void indexWordByFrequency(AnalisysData data){
		this.frequenciesErrorMap = new HashMap<Integer, Map<String, IWordInText>>();
		this.frequenciesCorrectMap = new HashMap<Integer, Map<String, IWordInText>>();
		this.frequenciesWordMap = new HashMap<Integer, Map<String, IWordInText>>();

		Iterator<Entry<String, IWordOccurrence>> errorIterator = data.errorTree.iterator();
		while(errorIterator.hasNext()){
			this.indexWordByFrequency(errorIterator.next().getValue());
		}

		Iterator<IWordOccurrence> correctIterator = data.correctList.iterator();
		while(correctIterator.hasNext()){
			this.indexWordByFrequency(correctIterator.next());
		}
	}

	private void indexWordByFrequency(IWordOccurrence word){
		int frequency = word.getFrequency();
		String wordString = word.getWord();

		this.getFrequencyMap(this.frequenciesWordMap, frequency).put(wordString, word);

		if(word.isCorrect())
			this.getFrequencyMap(this.frequenciesCorrectMap, frequency).put(wordString, word);
		else
			this.getFrequencyMap(this.frequenciesErrorMap, frequency).put(wordString, word);
	}

	private Map<String, IWordInText> getFrequencyMap(Map<Integer, Map<String, IWordInText>> mapByFrequency, int frequency){
		Map<String, IWordInText> frequencyMap = mapByFrequency.get(frequency);
		if(frequencyMap == null) {
			frequencyMap = new AVLTree<String, IWordInText>();
			mapByFrequency.put(frequency, frequencyMap);
		}

		return frequencyMap;
	}
	
	@Override
	public Iterator<IWordInText> correct(int frequency) {
		//TODO improve with values iterator
		return this.getFrequencyMap(this.frequenciesCorrectMap, frequency).values().iterator();
	}
	
	@Override
	public Iterator<IWordInText> errors(int frequency) {
		//TODO improve with values iterator
		return this.getFrequencyMap(this.frequenciesErrorMap, frequency).values().iterator();
	}

	@Override
	public Iterator<IWordInText> errors() {
		//TODO improve with values iterator
		return this.errorTree.values().iterator();
	}
	
	@Override
	public int frequency(String word) {
		IWordInText wordOccurrence = this.wordMap.get(word.toLowerCase());
		if (wordOccurrence != null)
			return wordOccurrence.getFrequency();
		return 0;
	}

	@Override
	public Iterator<IWordInText> occurrences(int frequency) {
		//TODO improve with values iterator
		return this.getFrequencyMap(this.frequenciesWordMap, frequency).values().iterator();
	}
	
}
