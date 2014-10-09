/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 *
 */
public enum Command {

	/**
	 * Add word
	 */
	AD,
	
	/**
	 * Search word
	 */
	PC,
	
	/**
	 * Add text
	 */
	AT,
	
	/**
	 * Remove text
	 */
	RT,
	
	/**
	 * List text
	 */
	LT,
	
	/**
	 * List text excerpt
	 */
	LR,
	
	/**
	 * List errors
	 */
	LE,
	
	/**
	 * Get word frequency
	 */
	FP,
	
	/**
	 * List word frequency
	 */
	LF,
	
	/**
	 * Invalid command
	 */
	INVALID;
}
