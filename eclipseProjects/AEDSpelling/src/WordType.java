import java.io.Serializable;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 *
 */
public enum WordType implements Serializable {
	/**
	 * Correct word
	 */
	C,
	/**
	 * Wrong word
	 */
	E,
	/**
	 * Word
	 */
	P;
}
