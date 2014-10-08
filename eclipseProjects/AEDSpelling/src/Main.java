import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import aed.dataStructures.Iterator;
import aed.dataStructures.LinkedList;
import aed.dataStructures.List;
import aed.spelling.*;
import aed.spelling.app.ISpelling;
import aed.spelling.app.IWordOccurrence;


public class Main {
		
	public static final String DATA_STORE_FILE = "file";
	
	public static void main(String [] args) {
			
		Scanner scan = new Scanner(System.in);
		Command command;
		String output = null;
		
		ISpelling spelling = initializeSpelling(); 			
		
		while (scan.hasNextLine()) {
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
			case LR:
				output = listTextExcerpt(spelling, scan);
				break;
			case LE:
				output = listError(spelling, scan);
				break;
			case FP:
				output = getWordFrequency(spelling, scan);
				break;
			case LF:
				output = listWordFrequency(spelling, scan);
				break;
			case INVALID:
			default:
				output = Output.UNKNOWN_COMMAND.message();
				break;
			} 
			System.out.println(output);
		} 
		
		storeData(spelling, DATA_STORE_FILE);
		scan.close();
	}

	/**
	 * Converts a string to a command, if no match is found returns Command.INVALID
	 * 
	 * @param in Input scanner 
	 * @return Command matching the input
	 */
	private static Command getCommand(Scanner in) {
		try {
			String input = in.next().toUpperCase().trim();
			return Command.valueOf(input);
		} catch (IllegalArgumentException e) {
			return Command.INVALID;
		}
	}
	
	/**
	 * Add a word specified by input to the dictionary
	 * 
	 * @param spelling Instance containing the dictionary to add word to
	 * @param scan Input scanner 
	 * @return Output string
	 */
	private static String addWordsToDictionary(ISpelling spelling, Scanner scan) {
		int numberOfWords;
		try {
			numberOfWords= scan.nextInt();
			scan.nextLine();
		} catch (InputMismatchException e) {
			return Output.INPUT_ERROR.message();
		}

		List<String> newWords = new LinkedList<String>();
		for (int i = 0; i < numberOfWords; i++) {
			newWords.addLast(processInput(scan.nextLine()));
		}
		
		boolean anyAdded ;
		try {
			anyAdded = spelling.addWords(newWords);
		} catch (InvalidWordException e) {
			return Output.INPUT_ERROR.message();
		}
		
		return anyAdded ? Output.ADD_WORDS_SUCCESS.message() : Output.ADD_WORDS_FAILED.message();	
	} 
	
	/**
	 * Search a word specified by input in the dictionary
	 * 
	 * @param spelling Instance containing the dictionary to search in
	 * @param scan Input scanner 
	 * @return Output string
	 */
	private static String searchWordInDictionary(ISpelling spelling, Scanner scan) {
		String word = processInput(scan.nextLine());
		return spelling.verifyWord(word) ? Output.WORD_FOUND.message() : Output.WORD_NOT_FOUND.message();
	}
	
	/**
	 * Add a text specified by input
	 * 
	 * @param spelling Instance to add the text to
	 * @param scan Input scanner 
	 * @return Output string
	 */
	private static String addText(ISpelling spelling, Scanner scan) {	
		String textId = processInput(scan.next());
				
		int numberOfLines;
		try {
			numberOfLines = scan.nextInt();
			scan.nextLine();
		} catch (InputMismatchException e) {
			return Output.INPUT_ERROR.message();
		}
		
		List<String> textLines = new LinkedList<String>();
		
		for(int i = 0; i < numberOfLines; i++) {
			textLines.addLast(scan.nextLine());
		}
		
		boolean wasAdded = spelling.addText(textId, textLines);
		
		return wasAdded ? Output.ADD_TEXT_SUCCESS.message() : Output.ADD_TEXT_FAILED.message();
	}
	
	/**
	 * Remove a text specified by input
	 * 
	 * @param spelling Instance to remove the text from
	 * @param scan Input scanner 
	 * @return Output string
	 */
	private static String removeText(ISpelling spelling, Scanner scan) {	
		String textId = processInput(scan.nextLine());		
		boolean wasRemoved = spelling.delText(textId);
		
		return wasRemoved ? Output.REMOVE_TEXT_SUCCESS.message() : Output.TEXT_NOT_FOUND.message();
	}
	
	/**
	 * Process input
	 * @param input Input to process
	 * @return Processed input
	 */
	private static String processInput(String input) {
		return input.trim();
	}
	
	private static String listWordFrequency(ISpelling spelling, Scanner scan) {
		String textId = scan.next();
		WordType wType;
		int freq;
		
		try {
			wType = WordType.valueOf(scan.next());
		} catch (IllegalArgumentException e) {
			scan.nextLine();
			return Output.UNKNOWN_WORD_TYPE.message();
		}
		
		try {
			freq = scan.nextInt();
		} catch (InputMismatchException e) {
			scan.nextLine();
			return Output.INPUT_ERROR.message();
		}
		
		String output = new String();
		
		Iterator<IWordOccurrence> words = null; 
		
		switch(wType){
		case C:	
			words = spelling.textCorrects(textId);
			break;
		case E:
			words = spelling.textErrors(textId);
			break;
		case P:
			words = spelling.wordsOf(textId);
			break;
		}
		
		if(words == null)
			output += Output.TEXT_NOT_FOUND;
		else
			while(words.hasNext()){
				IWordOccurrence word = words.next();
				if(word.getFrequency() == freq)
					output += word.getWord() + "\n";
			}
		
		return output + "\n";
	}

	private static String getWordFrequency(ISpelling spelling, Scanner scan) {
		String textId = processInput(scan.nextLine());		
		String word = processInput(scan.nextLine());
		int wordFrequency =  spelling.frequencyOf(textId, word);
		
		return (wordFrequency >= 0) ? Output.LIST_ERRORS_SUCCESS.message(Integer.toString(wordFrequency)) : Output.TEXT_NOT_FOUND.message();
	}

	private static String listError(ISpelling spelling, Scanner scan) {
		String textId = scan.next();
		
		Iterator<IWordOccurrence> errors = spelling.textErrors(textId);
		
		if(errors == null)
			return Output.TEXT_NOT_FOUND.message();
		
		if(!errors.hasNext())
			return Output.LIST_ERRORS_FAIL.message();
		
		String output = new String();
		do{
			IWordOccurrence error = errors.next();
			Iterator<Integer> linesN = error.linesNr();
			output += error.getWord() + "\n";
			do{
				output += linesN.next() + "\n";
			}
			while(linesN.hasNext());
		}
		while(errors.hasNext());
		
		return output + "\n";
	}

	private static String listTextExcerpt(ISpelling spelling, Scanner scan) {
		String textId = processInput(scan.nextLine());
		
		int firstLine;
		int lastLine;
		try {
			firstLine = scan.nextInt();
			lastLine = scan.nextInt();
			scan.nextLine();
		} catch (InputMismatchException e) {
			return Output.INPUT_ERROR.message();
		}
		
		Iterator<Line> iterator = spelling.textLines(textId, firstLine, lastLine);
		return (iterator != null) ? listLines(iterator) : Output.TEXT_NOT_FOUND.message();
	}
	
	private static String listText(ISpelling spelling, Scanner scan) {
		String textId = processInput(scan.nextLine());
		Iterator<Line> iterator = spelling.textLines(textId);
		return (iterator != null) ? listLines(iterator) : Output.TEXT_NOT_FOUND.message();
	}
	
	private static String listLines(Iterator<Line> iterator) {
		String output = "";
		
		while (iterator.hasNext()) {
			output += iterator.next().getLine();
		}
		
		return output;
	}
	
	private static void storeData(ISpelling spelling, String filePath){
		try {
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(filePath));
				file.writeObject(spelling);
				file.flush();
				file.close();
			}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static ISpelling loadData(String filePath){
		try{ 
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(filePath));
			ISpelling spelling  = (ISpelling) file.readObject();
			file.close();
			return spelling;
		}
		catch (IOException e) {
			return null;
		}
		catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	private static ISpelling initializeSpelling() {
		ISpelling loadedData = loadData(DATA_STORE_FILE);
		return (loadedData != null) ? loadedData : new Spelling();
	}
	
}
