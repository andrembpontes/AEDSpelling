import java.util.InputMismatchException;
import java.util.Scanner;

import aed.dataStructures.LinkedList;
import aed.dataStructures.List;
import aed.spelling.InvalidWordException;
import aed.spelling.Spelling;
import aed.spelling.app.ISpelling;


public class Main {
	
	public static void main(String [] args) {		
		
		Scanner scan = new Scanner(System.in);
		Command command;
		String output = null;
		ISpelling spelling = new Spelling();
		boolean exit = false;
		
		do {
			command = getCommand(scan);
			switch (command) {
			case AD:
				output = addWordsToDictionary(spelling, scan);
				break;
			case INVALID:
			default:
				output = Output.UNKNOWN_COMMAND;
				break;
			} 
			System.out.println(output);
		} while (!exit);
		
		scan.close();
	}
	
	private static Command getCommand(Scanner in) {
		try {
			String input = in.next().toUpperCase().trim();
			return Command.valueOf(input);
		} catch (IllegalArgumentException e) {
			return Command.INVALID;
		}
	}
	
	private static String addWordsToDictionary(ISpelling spelling, Scanner scan) {
		int numberOfWords;
		try {
			numberOfWords= scan.nextInt();
		} catch (InputMismatchException e) {
			return Output.INPUT_ERROR;
		}

		List<String> newWords = new LinkedList<String>();
		for (int i = 0; i < numberOfWords; i++) {
			newWords.add(scan.nextLine());
		}
		
		boolean anyAdded ;
		try {
			anyAdded = spelling.addWords(newWords);
		} catch (InvalidWordException e) {
			return Output.INPUT_ERROR;
		}
		
		return anyAdded ? Output.WORDS_ADDED_SUCCESS : Output.WORDS_ADDED_FAILED;	
	} 
}