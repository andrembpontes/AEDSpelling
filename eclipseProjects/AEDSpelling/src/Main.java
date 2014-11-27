import aed.dataStructures.Iterator;
import aed.dataStructures.LinkedList;
import aed.dataStructures.List;
import aed.spelling.InvalidLineNumberException;
import aed.spelling.InvalidLineRangeException;
import aed.spelling.InvalidWordException;
import aed.spelling.Line;
import aed.spelling.app.ISpelling;
import aed.spelling.app.IWordInText;
import aed.spelling.app.Spelling;
import aed.spelling.app.TextNotFoundException;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 */
public class Main {
	
	public static Printer		OUT				= new Printer();
	
	public static InputStream	IN				= System.in;
	
	public static final String	DATA_STORE_FILE	= "store.data";
	
	public static final String	LINE_BREAK		= "\n";
	
	public static final String	CLEAN_ARG		= "CLEAN";
	
	public static void main(String... args) {
		// Initialise ins / outs
		Scanner scan = new Scanner(Main.IN);
		Printer printer = new Printer();
		
		// Verify if has to load stored data
		ISpelling spelling;
		if (args.length > 0 && args[0].equals(Main.CLEAN_ARG))
			spelling = new Spelling();
		else
			spelling = Main.initializeSpelling();
		
		// Start command interpretation
		Command command;
		while (scan.hasNext()) {
			try {
				command = Main.getCommand(scan);
				switch (command) {
					case AD:
						Main.addWordsToDictionary(spelling, scan);
						break;
					case PC:
						Main.searchWordInDictionary(spelling, scan);
						break;
					case AT:
						Main.addText(spelling, scan);
						break;
					case RT:
						Main.removeText(spelling, scan);
						break;
					case LT:
						Main.listText(spelling, scan);
						break;
					case LR:
						Main.listTextExcerpt(spelling, scan);
						break;
					case LE:
						Main.listError(spelling, scan);
						break;
					case FP:
						Main.getWordFrequency(spelling, scan);
						break;
					case LF:
						Main.listWordFrequency(spelling, scan);
						break;
					case INVALID:
					default:
						printer.printMsg(Output.UNKNOWN_COMMAND);
						break;
				}
			}
			catch (InputMismatchException e) {
				OUT.printMsg(Output.INPUT_ERROR);
				scan.nextLine();
			}
			catch (TextNotFoundException e1) {
				OUT.printMsg(Output.TEXT_NOT_FOUND);
			}
			
			OUT.println();
		}
		
		Main.storeData(spelling, Main.DATA_STORE_FILE);
		scan.close();
		

	}
	
	/**
	 * Add a text specified by input
	 * 
	 * @param spelling Instance to add the text to
	 * @param scan Input scanner
	 */
	private static void addText(ISpelling spelling, Scanner scan) {
		String textId = Main.processInput(scan.next());
		
		int numberOfLines = scan.nextInt();
		scan.nextLine();
		List<String> textLines = new LinkedList<String>();
		
		for (int i = 0; i < numberOfLines; i++)
			textLines.add(scan.nextLine());
		
		boolean wasAdded = spelling.addText(textId, textLines);
		
		OUT.printMsg(wasAdded ? Output.ADD_TEXT_SUCCESS : Output.ADD_TEXT_FAILED);
	}
	
	/**
	 * Add a word specified by input to the dictionary
	 * 
	 * @param spelling Instance containing the dictionary to add word to
	 * @param scan Input scanner
	 */
	private static void addWordsToDictionary(ISpelling spelling, Scanner scan) {
		int numberOfWords = scan.nextInt();
		scan.nextLine();
		
		List<String> newWords = new LinkedList<String>();
		for (int i = 0; i < numberOfWords; i++)
			newWords.add(Main.processInput(scan.nextLine()));
		
		try {
			OUT.printMsg(spelling.addWords(newWords) ? Output.ADD_WORDS_SUCCESS : Output.ADD_WORDS_FAILED);
			
		}
		catch (InvalidWordException e) {
			OUT.printMsg(Output.INPUT_ERROR);
		}
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
		}
		catch (IllegalArgumentException e) {
			return Command.INVALID;
		}
	}
	
	/**
	 * Print the frequency of a word on a text, both specified by input
	 * 
	 * @param spelling Instance to get the word and text from
	 * @param scan Input scanner
	 */
	private static void getWordFrequency(ISpelling spelling, Scanner scan) {
		String textId = Main.processInput(scan.next());
		String word = Main.processInput(scan.nextLine());
		int wordFrequency = spelling.frequencyOf(textId, word);
		
		if (wordFrequency >= 0)
			OUT.printMsg(Output.LIST_ERRORS_SUCCESS, word, Integer.toString(wordFrequency));
		else
			OUT.printMsg(Output.TEXT_NOT_FOUND);
	}
	
	/**
	 * Initialize ISpelling instance with old data if available
	 * 
	 * @return Initialized ISpelling instance
	 */
	private static ISpelling initializeSpelling() {
		ISpelling loadedData = Main.loadData(Main.DATA_STORE_FILE);
		return loadedData != null ? loadedData : new Spelling();
	}
	
	/**
	 * List all errors on text specified by input
	 * 
	 * @param spelling Instance to get the text from
	 * @param scan Input scanner
	 */
	private static void listError(ISpelling spelling, Scanner scan) {
		String textId = scan.next();
		
		Iterator<IWordInText> errors = spelling.textErrors(textId);
		
		if (!errors.hasNext())
			OUT.printMsg(Output.LIST_ERRORS_FAIL);
		else {
			do {
                IWordInText error = errors.next();
				Iterator<Integer> linesN = error.linesNr();
				OUT.println(error.getWord());
				
				do
					OUT.println(linesN.next());
				while (linesN.hasNext());
			}
			while (errors.hasNext());
		}
	}
	
	/**
	 * List text lines
	 * 
	 * @param iterator Iterator of text lines
	 * @return Text excerpt
	 */
	private static void listLines(Iterator<Line> iterator) {
		while (iterator.hasNext())
			OUT.println(iterator.next().getLine());
	}
	
	/**
	 * List an entire text specified by input
	 * 
	 * @param spelling Instance to get the text from
	 * @param scan Input scanner
	 */
	private static void listText(ISpelling spelling, Scanner scan) {
		String textId = Main.processInput(scan.nextLine());
		Iterator<Line> iterator = spelling.textLines(textId);
		
		Main.listLines(iterator);
	}
	
	/**
	 * List a text excerpt specified by input
	 * 
	 * @param spelling Instance to get the text from
	 * @param scan Input scanner
	 */
	private static void listTextExcerpt(ISpelling spelling, Scanner scan) {
		String textId = Main.processInput(scan.next());
		
		int firstLine;
		int lastLine;
		
		firstLine = scan.nextInt();
		lastLine = scan.nextInt();
		scan.nextLine();
		
		try {
			Main.listLines(spelling.textLines(textId, firstLine, lastLine));
		}
		catch (InvalidLineNumberException e) {
			OUT.printMsg(Output.EXCERPT_NOT_FOUND);
		}
		catch (InvalidLineRangeException e) {
			OUT.printMsg(Output.INVALID_LINE_INTERVAL);
		}
	}
	
	/**
	 * List the frequency of all words of given type on a text specified by input
	 * 
	 * @param spelling Instance to get the text from
	 * @param scan Input scanner
	 */
	private static void listWordFrequency(ISpelling spelling, Scanner scan) {
		String textId = scan.next();
		WordType wType;
		int freq;
		
		try {
			wType = WordType.valueOf(scan.next());
			freq = scan.nextInt();

			Iterator<IWordInText> words = null;

			switch (wType) {
				case C:
					words = spelling.textCorrects(textId, freq);
					break;
				case E:
					words = spelling.textErrors(textId, freq);
					break;
				case P:
					words = spelling.wordsWithFrequency(textId, freq);
					break;
			}

			if (words.hasNext()) {
				do {
					OUT.println(words.next().getWord());
				} while (words.hasNext());
			}
			else
				OUT.printMsg(Output.NO_WORD_WITH_FREQUENCY);


		}
		catch (IllegalArgumentException e) {
			scan.nextLine();
			OUT.printMsg(Output.UNKNOWN_WORD_TYPE);
		}
	}
	
	/**
	 * Load ISpelling instance from a file
	 * 
	 * @param filePath File path
	 * @return Spelling instance loaded from a file
	 */
	private static ISpelling loadData(String filePath) {
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(filePath));
			ISpelling spelling = (ISpelling) file.readObject();
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
	
	/**
	 * Process input
	 * 
	 * @param input Input to process
	 * @return Processed input
	 */
	private static String processInput(String input) {
		return input.trim();
	}
	
	/**
	 * Remove a text specified by input
	 * 
	 * @param spelling Instance to remove the text from
	 * @param scan Input scanner
	 */
	private static void removeText(ISpelling spelling, Scanner scan) {
		String textId = Main.processInput(scan.nextLine());
		
		if (spelling.delText(textId))
			OUT.printMsg(Output.REMOVE_TEXT_SUCCESS);
		else
			OUT.printMsg(Output.TEXT_NOT_FOUND);
	}
	
	/**
	 * Search a word specified by input in the dictionary
	 * 
	 * @param spelling Instance containing the dictionary to search in
	 * @param scan Input scanner
	 */
	private static void searchWordInDictionary(ISpelling spelling, Scanner scan) {
		String word = Main.processInput(scan.nextLine());
		
		if (spelling.verifyWord(word))
			OUT.printMsg(Output.WORD_FOUND);
		else
			OUT.printMsg(Output.WORD_NOT_FOUND);
	}
	
	/**
	 * Store ISpelling instance from a file
	 * 
	 * @param spelling Instance to store
	 * @param filePath File path
	 */
	private static void storeData(ISpelling spelling, String filePath) {
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
}
