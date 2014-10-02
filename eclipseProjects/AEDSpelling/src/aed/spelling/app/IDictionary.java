package aed.spelling.app;

public interface IDictionary {

	/**
	 * Adds a word to the dictionary
	 * 
	 * @param word Word to add 
	 */
	void addWord(String word);
	
	/**
	 * Verifies if a word exists in the dictionary
	 * 
	 * @param word Word to verify
	 */
	boolean verifyWord(String word);
	
	
}
