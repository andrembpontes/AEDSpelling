package aed.dataStructures;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 */
public class EmptyListException extends RuntimeException {
	
	static final long	serialVersionUID	= 0L;
	
	public EmptyListException() {
		super();
	}
	
	public EmptyListException(String message) {
		super(message);
	}
	
}
