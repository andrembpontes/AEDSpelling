import java.util.InputMismatchException;
import java.util.Scanner;

import aed.dataStructures.LinkedList;
import aed.dataStructures.List;
import aed.spelling.InvalidWordException;
import aed.spelling.Line;
import aed.spelling.Spelling;
import aed.spelling.app.ISpelling;


public class Main {
	
	private static final String END_TEXT_TOKEN= "\n";

	
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
			case PC:
				output = searchWordInDictionary(spelling, scan);
				break;
			case AT:
				output = addText(spelling, scan);
				break;
			case RT:
				output = removeText(spelling, scan);
				break;
			case LT:
				output = listText(spelling, scan);
				break;
			case INVALID:
			default:
				output = Output.UNKNOWN_COMMAND.message();
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
			return Output.INPUT_ERROR.message();
		}

		List<String> newWords = new LinkedList<String>();
		for (int i = 0; i < numberOfWords; i++) {
			newWords.add(scan.nextLine());
		}
		
		boolean anyAdded ;
		try {
			anyAdded = spelling.addWords(newWords);
		} catch (InvalidWordException e) {
			return Output.INPUT_ERROR.message();
		}
		
		return anyAdded ? Output.ADD_WORDS_SUCCESS.message() : Output.ADD_WORDS_FAILED.message();	
	} 
	
	private static String searchWordInDictionary(ISpelling spelling, Scanner scan) {
		String word = scan.nextLine();
		
		return spelling.verifyWord(word) ? Output.WORD_FOUND.message() : Output.WORD_NOT_FOUND.message();
	}
	
	private static String addText(ISpelling spelling, Scanner scan) {	
		String textId = processInput(scan.nextLine());
				
		List<String> textLines = new LinkedList<String>();
		String line = scan.nextLine();
		
		while (!line.equals(END_TEXT_TOKEN)) {
			textLines.add(line);
			line = scan.nextLine();
		}
		
		boolean wasAdded = spelling.addText(textId, textLines);
		
		return wasAdded ? Output.ADD_TEXT_SUCCESS.message() : Output.ADD_TEXT_FAILED.message();
	}
	
	private static String removeText(ISpelling spelling, Scanner scan) {	
		String textId = processInput(scan.nextLine());		
		boolean wasRemoved = spelling.delText(textId);
		
		return wasRemoved ? Output.REMOVE_TEXT_SUCCESS.message() : Output.TEXT_NOT_FOUND.message();
	}
	
	
	private static String listText(ISpelling spelling, Scanner scan) {
		String textId = processInput(scan.nextLine());
		return "";
		//TODO: finish
	}
	
	private static String processInput(String input) {
		return input.trim();
	}
}