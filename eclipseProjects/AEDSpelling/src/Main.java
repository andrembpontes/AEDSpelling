import java.util.Scanner;

import aed.spelling.Spelling;
import aed.spelling.app.ISpelling;


public class Main {
	public static void main(String [] args) {
		
		Scanner in = new Scanner(System.in);
		Command command;
		String output = null;
		ISpelling spelling = new Spelling();
		boolean exit = false;
		
		do {
			command = getCommand(in);
			switch (command) {
			case INVALID:
			default:
				output = Output.UNKNOWN_COMMAND;
				break;
			} 
				
			System.out.println(output);
		} while (!exit);
		
		in.close();
	}
	
	private static Command getCommand(Scanner in) {
		try {
			String input = in.next().toUpperCase().trim();
			return Command.valueOf(input);
		} catch (IllegalArgumentException e) {
			return Command.INVALID;
		}
	}
}