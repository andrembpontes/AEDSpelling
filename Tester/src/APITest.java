import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;


public class APITest {

	String TEST_INPUT_DIR = "test/input/";
	String TEST_OUTPUT_DIR = "test/output/";
	String TEST_TEST_DIR = "test/test/";
	String CH_INPUT = "in";
	String CH_OUTPUT = "out";
	
	static int execs = 0;
	
	@Test
	public void test() throws IOException {
		
		boolean diffs = false;
		File[] inputs = new File(TEST_INPUT_DIR).listFiles();
		Arrays.sort(inputs);
		
		for(File input : inputs){
			String outputFileName = input.getName().replace(CH_INPUT, CH_OUTPUT);
			
			File myOutput = new File(TEST_TEST_DIR + "_test_" + outputFileName);
			
			
			PrintStream printStream = new PrintStream(new FileOutputStream(myOutput));
			
			Main.IN = new FileInputStream(input);
			Main.OUT = new Printer(printStream);
			
			
			System.out.println("Executing test " + input.getName());
			
			try{
				new MainExecution().executeMain(new FileInputStream(input), new Printer(new PrintStream(myOutput)));
			}
			catch(Exception e){
				System.err.println("BOOM!");
				e.printStackTrace();
			}
			
			System.out.println("Oppening expected output file... [" + outputFileName + "]");
			
			File output = new File(TEST_OUTPUT_DIR + outputFileName);
			
			if(output.exists()){

				Scanner myScan = new Scanner(myOutput);
				Scanner expScan = new Scanner(output);

				while(myScan.hasNext() && expScan.hasNext()){
					String myOut = myScan.nextLine();
					String expOut = expScan.nextLine();

					if(!myOut.equals(expOut)){
						diffs = true;
						System.err.println(myOut + " | <- my vs. expected -> | " + expOut);
					}
				}
				
				myScan.close();
				expScan.close();
				
			}
			else{
				System.err.println("No output expected for " + input.getName());
			}
			
		}
		
		if(diffs)
			fail("jaj�o!");
				
	}
	
	class MainExecution extends Thread{

		public void executeMain(InputStream in, Printer out){
			Main.IN = in;
			Main.OUT = out;
			this.run();
		}
		
		@Override
		public void run(){
			if(execs ++ == 0)
				Main.main(Main.CLEAN_ARG);
			else
				Main.main();
		}
	}

}
