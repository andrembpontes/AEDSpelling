package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.dataStructures.List;
import aed.spelling.Text;
import aed.spelling.Word;

public class AnalisableText extends Text implements IAnalisableText  {


	private IAnalysisResults analysisResults;
	private IDictionary dictionary;
	
	public AnalisableText(String id, List<String> lines, IDictionary dictionary) {
		super(id, lines);
		
		this.dictionary = dictionary;
	}

	@Override
	public Iterator<IWordOccurrence> errors() {
		if(this.analysisResults == null)
			this.analise();
		
		return this.analysisResults.errors();
	}

	@Override
	public int frequency(Word word) {
		return this.analysisResults.frequency(word);
	}

	@Override
	public Iterator<IWordOccurrence> occurrences() {
		return this.analysisResults.occurrences();
	}
	
	private void analise(){
		this.analysisResults = new AnalisysResults(this, this.dictionary);
	}

}
