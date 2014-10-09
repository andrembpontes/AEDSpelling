package aed.spelling;

import java.io.Serializable;

/**
 * Represents a text line with correspondent position in text
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public class Line implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	
	private String				line;
	private int					nr;
	
	/**
	 * Create a line
	 *
	 * @param nr Line's position in text
	 * @param line Line content
	 */
	public Line(int nr, String line) {
		this.nr = nr;
		this.line = line;
	}
	
	/**
	 * Return line content
	 *
	 * @return line Content
	 */
	public String getLine() {
		return this.line;
	}
	
	/**
	 * Return line position in text
	 *
	 * @return line Position in text
	 */
	public int getNr() {
		return this.nr;
	}
}
