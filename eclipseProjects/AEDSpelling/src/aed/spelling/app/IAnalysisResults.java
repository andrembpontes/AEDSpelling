package aed.spelling.app;

import aed.dataStructures.List;
import aed.dataStructures.Iterator;

public interface IAnalysisResults {
	
	Iterator<IWordOccurrence> errors();
	
	int frequency(String word);
	
	Iterator<IWordOccurrence> occurrences();	
}

