import java.io.PrintStream;

import aed.dataStructures.Iterator;


/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 *
 */
public class Printer {
	public static final PrintStream DEFAULT_PRINT_STREAM = System.out;
	
	private PrintStream out;
	
	public Printer(PrintStream out){
		this.out = out;
	}
	
	public Printer(){
		this(DEFAULT_PRINT_STREAM);
	}
	
	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}
	
	public void flush(){
		this.out.flush();
	}
	
	public void print(String toPrint){
		this.out.print(toPrint);
	}
	
	public void println(String toPrint){
		this.out.println(toPrint);
	}
	
	public void println(int i){
		this.out.println(i);
	}
	
	public void println(){
		this.out.println();
	}
	
	public void printMsg(Output toPrint){
		this.println(toPrint.message());
		//this.println();
	}
	
	public void printMsg(Output toPrint, String... args){
		this.println(toPrint.message(args));
		//this.println();
	}
	
	public void printIterator(Iterator<?> iterator){
		while(iterator.hasNext()){
			this.println(iterator.next().toString());
		}
		
		this.println();
	}

}
