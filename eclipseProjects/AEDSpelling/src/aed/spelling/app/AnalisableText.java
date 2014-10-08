package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.dataStructures.List;
import aed.spelling.Text;

public class AnalisableText extends Text implements IAnalisableText  {


	private IAnalysisResults analysisResults;
	private IDictionary dictionary;
	
	public AnalisableText(String id, List<String> lines, IDictionary dictionary) {
		super(id, lines);
		
		this.dictionary = dictionary;
	}

	@Override
	public Iterator<IWordOccurrence> errors() {
		checkAnalisys();
		return this.analysisResults.errors();
	}

	@Override
	public int frequency(String word) {
		checkAnalisys();
		return this.analysisResults.frequency(word);
	}

	@Override
	public Iterator<IWordOccurrence> occurrences() {
		checkAnalisys();
		return this.analysisResults.occurrences();
	}
	
	private void analise(){
		this.analysisResults = new AnalisysResults(this, this.dictionary);
	}
	
	private void checkAnalisys() {
		if(this.analysisResults == null)
			this.analise();		
	}

}
