package aed.spelling.app;

import aed.dataStructures.Iterator;
import aed.dataStructures.List;
import aed.spelling.Text;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
class AnalisableText extends Text implements IAnalisableText {
	
	private static final long	serialVersionUID	= 1L;
	private IAnalysisResults	analysisResults;
	private IDictionary			dictionary;
	
	public AnalisableText(String id, List<String> lines, IDictionary dictionary) {
		super(id, lines);
		
		this.dictionary = dictionary;
	}
	
	/**
	 * Analysis a text 
	 */
	private void analise() {
		this.analysisResults = new AnalisysResults(this, this.dictionary);
	}
	
	/**
	 * Verifies if a text was already analised
	 */
	private void checkAnalisys() {
		if (this.analysisResults == null)
			this.analise();
	}

	@Override
	public Iterator<IWordInText> correct(int frequency) {
		this.checkAnalisys();
		return this.analysisResults.correct(frequency);
	}
	
	@Override
	public Iterator<IWordInText> errors(int frequency) {
		this.checkAnalisys();
		return this.analysisResults.errors(frequency);
	}

	@Override
	public Iterator<IWordInText> errors() {
		this.checkAnalisys();
		return this.analysisResults.errors();
	}
	
	@Override
	public int frequency(String word) {
		this.checkAnalisys();
		return this.analysisResults.frequency(word);
	}
	
	@Override
	public Iterator<IWordInText> occurrences(int frequency) {
		this.checkAnalisys();
		return this.analysisResults.occurrences(frequency);
	}
	
}
