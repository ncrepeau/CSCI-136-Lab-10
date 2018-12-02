import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver {
	static File bitterroot;
	static File coyote;
	static Scanner myFileScanner;
	static ArrayList<String> bitterrootArray = new ArrayList<String>();
	static Scanner myCoyoteScanner;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		bitterroot = new File("./src/Coyote and the Bitterroot Valley.txt");
		coyote = new File("./src/Coyote.txt");
		myCoyoteScanner = new Scanner(coyote);
		myFileScanner = new Scanner(bitterroot);
		readInBitterroot(myFileScanner);
		readInCoyote(myCoyoteScanner);
		Sorting mySort = new Sorting();
		String[] array = new String[2084];
		for (int i = 0; i < bitterrootArray.size(); i++) {
			array[i] = bitterrootArray.get(i);
		}
		mySort.selectionSort(array);
//		for (int i = 0; i < array.length; i++) {
//			System.out.println(array[i]);
//		}
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		for (int i = 0; i < array.length; i++) {
			String s = array[i];
			if (wordCount.keySet().contains(s)) {
				int count = wordCount.get(s) + 1;
				wordCount.put(s, count);
			} else {
				wordCount.put(s, 1);
			}
		}
		Integer freq = null;
		String mostFreq = null;
		for(String s : wordCount.keySet()) {
			int i = wordCount.get(s);
			if(freq == null) {
				freq = i;
			}
			if(i > freq) {
				freq = i;
				mostFreq = s;
			}
		}
		System.out.println("The most occuring word \"" + mostFreq + "\" occured " + freq + " times");

		Scanner msc = new Scanner(System.in);
		System.out.println("Would you like to search for a certian words frequency? Y/N");
		String ans = msc.nextLine();
		while (ans.equals("Y")) {
			System.out.println("What word would you like to search for?");
			String target = msc.nextLine();
			target = target.toLowerCase();
			int count = search(bitterrootArray, target);
			System.out.println(target + ": " + count);
			System.out.println("Would you like to search for another word? Y/N");
			ans = msc.nextLine();
		}

	}

	public static void readInBitterroot(Scanner myFileScan) throws FileNotFoundException {
		if (myFileScan.hasNext()) {
			String word = myFileScan.next();
			word = word.toLowerCase();
			word = word.replaceAll("\\p{Punct}", "");
			word.trim();
			if (word.equals("the") || word.equals("and") || word.equals("it") || word.equals("a") || word.equals("on")
					|| word.equals("in")) {
				word = myFileScan.next();
				word = word.toLowerCase();
				word = word.replaceAll("\\p{Punct}", "");
				word.trim();
				bitterrootArray.add(word);
				readInBitterroot(myFileScan);
			} else {
				bitterrootArray.add(word);
				readInBitterroot(myFileScan);
			}
		}

	}

	public static void readInCoyote(Scanner myCoyoteScan) throws FileNotFoundException {
		if (myCoyoteScan.hasNext()) {
			String cWord = myCoyoteScan.next();
			cWord = cWord.toLowerCase();
			cWord = cWord.replaceAll("\\p{Punct}", "");
			cWord.trim();
			if (cWord.equals("the") || cWord.equals("and") || cWord.equals("it") || cWord.equals("a")
					|| cWord.equals("on") || cWord.equals("in")) {
				cWord = myCoyoteScan.next();
				cWord = cWord.toLowerCase();
				cWord = cWord.replaceAll("\\p{Punct}", "");
				cWord.trim();
				bitterrootArray.add(cWord);
				readInCoyote(myCoyoteScan);
			} else {
				bitterrootArray.add(cWord);
				readInCoyote(myCoyoteScan);
			}
		}
	}

	public static int search(ArrayList<String> array, String target) {
		int counter = 0;
		for (int i = 0; i < array.size(); i++) {
			if (target.equals(array.get(i))) {
				counter++;
			}
		}
		return counter;
	}
}
