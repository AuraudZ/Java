
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class WordFrequencyTextAnalyzer {
	
	public static void main(String[] args) {
		String start = "a";
		String end = "z";
		String searchWord = "the";
		
		// *** Uncomment if you want to use the command line to pass in the file name
//		if (args.length != 1) {
//			System.err.println("Usage: java WordFrequencyTextAnalyzer fileName");
//			return;
//		}
		//File file = new File(args[0]);
		
		// Otherwise, just pass in file name here if using Eclipse...
		File file = new File("quote.txt");
		
		
		BinarySearchTree bst = new BinarySearchTree();
		
		if (!file.exists()) {
			System.err.println("File does not exist");
			return;
		}
		if (file.isDirectory()) {
			System.err.println(args[0] + " is a directory.");
			return;
		}
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String nextWord = scanner.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
				if (nextWord.length() == 0)
					continue;
				bst.put(nextWord);
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("File was not found");
		}
		
		System.out.println("-- Number of unique words: " + bst.size());
		System.out.println("-- Number of total words: " + bst.getTotalWordCount());
		System.out.println("-- The word \"" + searchWord + "\" appeared " + bst.getFrequency(searchWord) + " times.");

		System.out.println("-- Printing words in tree between \"" + start + "\" (inclusive) and \"" + end + "\" (exclusive): ");
		bst.printRange(start, end);
	}

}
