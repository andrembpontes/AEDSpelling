package aed.dataStructures;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 */
public class InvalidPositionException extends RuntimeException {
	
	static final long	serialVersionUID	= 0L;
	
	public InvalidPositionException() {
		super();
	}
	
	public InvalidPositionException(int index, int counter) {
		System.err.println("InvalidIndex: " + index + "; Counter; " + counter);
	}
	
	public InvalidPositionException(String message) {
		super(message);
	}
	
}
