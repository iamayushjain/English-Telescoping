package com.nlpl.week1.CompressedProject.Original;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.nlpl.week1.MaxMatchingAlgorithm;

public class PairOrWordSelection {
	final static Map<String, Integer> counterWord = new HashMap<String, Integer>();
	final static Map<String, Integer> counterPair = new HashMap<String, Integer>();
	final static Map<String, Integer> counterSymbol = new HashMap<String, Integer>();
	final static LinkedHashMap<String, Double> counter = new LinkedHashMap<String, Double>();
	public static int countPairInCounter = 0;
	public static String writerFileX="", fileText="";

	public static ArrayList<String> writerFileListWord;
	public static ArrayList<String> writerFileListSymbols;

	public static void main(String args[]) {
		inputFile();

	}

	public static void inputFile() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("D:\\Compressed Folder Projects\\file01.txt"));
			out("File Exists");

			List<String> list2 = new ArrayList<String>();
			List<String> list3 = new ArrayList<String>();

			String s;
			out("Reading File");
			while ((s = bf.readLine()) != null) {
				// s=bf.readLine();
				fileText = fileText + s + "\n";

			}
			out("File Completly Loaded");

			out(fileText);

			fileText = WordTokenization.tokenizationIssues(fileText);
			out("Word Split");
			splitFileWord(fileText);
			out("Pair Split");
			//splitFilePair(fileText);
			out("Symbol Split");
			splitFileSymbol(fileText);
			generateX();
			out("Output");
			long probDenemo = 0;
			for (String key : counterWord.keySet()) {
				System.out.println(key + " " + counterWord.get(key));
			}
			List<String> list = new ArrayList<String>(counterWord.keySet());
			Collections.sort(list, new Comparator<String>() {
				@Override
				public int compare(String x, String y) {
					return counterWord.get(y) - counterWord.get(x);
				}
			});

			out(Arrays.toString(list.toArray(new String[list.size()])));
			for (String str : list) {
				int frequency = counterWord.get(str);
				probDenemo += frequency;
				System.out.println(str + ":" + frequency + ", ");
			}
			out(probDenemo);
			double checkProb = 0;
			for (String str : list) {
				int frequency = counterWord.get(str);

				counter.put(str, (double) frequency / probDenemo);
				checkProb += (double) frequency / probDenemo;
				out(counter.get(str));
			}
			out(checkProb);
			for (String key : counter.keySet()) {
				System.out.println(key + " " + counter.get(key));
			}
			List<String> list1 = new ArrayList<String>(counter.keySet());
			Collections.sort(list1, new Comparator<String>() {
				@Override
				public int compare(String x, String y) {
					return (int) (counter.get(y) - counter.get(x));
				}
				
				
			});
			for (String str : list) {
				double frequency = counter.get(str);
				System.out.println(str + ":" + frequency + ", ");
			}

			StringBuilder encodedFile=(MaxMatchingAlgorithm.maxMatching
			(writerFileX,ShannonFano.compress(counter)));
			try {
//				PrintWriter writer = new PrintWriter("D:\\Compressed Folder Projects\\file3.txt", "UTF-8");
//				writer.write("Hello\nBAby");
	//
//				// writer.println("The second line");
//				writer.close();
				File file = new File("D:\\Compressed Folder Projects\\file04.txt");

				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				//=encodedFile.replaceAll("\n", "\r\n");
				bw.write(encodedFile.toString());
				bw.close();

				System.out.println("Done");
			} catch (Exception e) {

			}


			//
			// for (Map.Entry<String, Integer> entry : counterWord.entrySet()) {
			// String key = entry.getKey().toString();
			// Integer value = entry.getValue();
			// System.out.println("key, " + key + " value " + value);
			// }
			// String newFileText="";
			// for(int i=0; i<fileText.length();i++)
			// {
			// String wordFromFiletext=fileText.charAt(i)+"";
			// if(!wordFromFiletext.matches("[A-Za-z]"))
			// {
			// newFileText=newFileText+"\n";
			// }
			// else
			// {
			// newFileText=newFileText+wordFromFiletext;
			// }
			//
			// }
			// out(newFileText);
		} catch (Exception e) {
			out("Error" + e.getMessage());
		}
	}

	public static void out(Object o) {

		o = o.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n");
		System.out.println(o);
	}

	public static String LowerCase(String s) {
		return s.toLowerCase();
	}

	public static void splitFilePair(String fileText) {
		String split[] = LowerCase(fileText).split("[^A-Za-z]+");
		split = WordTokenization.porterAlgo(split);

		out(Arrays.toString(split));

		String str;
		for (int i = 0; i < split.length - 1; i++) {
			str = split[i] + " " + split[i + 1];

			counterPair.put(str, 1 + (counterPair.containsKey(str) ? counterPair.get(str) : 0));

		}
		List<String> list = new ArrayList<String>(counterPair.keySet());
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String x, String y) {
				return counterPair.get(y) - counterPair.get(x);
			}
		});

		out(Arrays.toString(list.toArray(new String[list.size()])));
		for (String string : list) {
			int frequency = counterPair.get(string);
			System.out.println(string + ":" + frequency + ", ");
			String splitPair[] = string.split(" ");
			int freq1, freq2;
			try {
				freq1 = counterWord.get(splitPair[0]);

				freq2 = counterWord.get(splitPair[1]);
				if ((freq1 == freq2)) {
					out("Working");
					countPairInCounter++;
					counterWord.remove(splitPair[0]);
					counterWord.remove(splitPair[1]);
					counterWord.put(string, frequency);
				}
			} catch (Exception e) {
				// out(e.getMessage());
			}

		}

	}

	public static void splitFileWord(String fileText) {
		String split[] = LowerCase(fileText).split("[^A-Za-z]+");
		//split = WordTokenization.porterAlgo(split);

		out("Non Sorted Array " + Arrays.toString(split));
		writerFileListWord = new ArrayList<String>();
		writerFileListSymbols = new ArrayList<String>();
		for (String str : split) {
			writerFileListWord.add(str);
			counterWord.put(str, 1 + (counterWord.containsKey(str) ? counterWord.get(str) : 0));
		}

		List<String> list = new ArrayList<String>(counterWord.keySet());
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String x, String y) {
				return counterWord.get(y) - counterWord.get(x);
			}
		});

		out("Sorted List " + Arrays.toString(list.toArray(new String[list.size()])));
		for (String str : list) {
			int frequency = counterWord.get(str);
			System.out.println(str + ":" + frequency + ", ");
		}
	}

	public static void splitFileSymbol(String fileText) {
		String split[] = LowerCase(fileText).split("[A-Za-z]+");

		out(Arrays.toString(split));

		for (String str : split) {
			writerFileListSymbols.add(str);
			counterSymbol.put(str, 1 + (counterSymbol.containsKey(str) ? counterSymbol.get(str) : 0));
		}

		List<String> list = new ArrayList<String>(counterSymbol.keySet());
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String x, String y) {
				return counterSymbol.get(y) - counterSymbol.get(x);
			}
		});

		out(Arrays.toString(list.toArray(new String[list.size()])));
		for (String str : list) {
			int frequency = counterSymbol.get(str);
			System.out.println(str + ":" + frequency + ", ");
			counterWord.put(str, frequency);
		}

	}

	public static void generateX() {
		int c1 = 0, c2 = 0;
		ArrayList<String> writerFileListFinal = new ArrayList<String>();
		System.out.println("Gene"+fileText);
		if (fileText.charAt(0) >= 'a' && fileText.charAt(0) <= 'z' ) {
			System.out.println("Gene Word");
			c2++;
			while (c1 < writerFileListWord.size() || c2 < writerFileListSymbols.size()) {

				if (c1 < writerFileListWord.size()) {
					writerFileListFinal.add(writerFileListWord.get(c1));
					out(writerFileListWord.get(c1));
					writerFileX+=writerFileListWord.get(c1);
					c1++;
				}

				if (c2 < writerFileListSymbols.size()) {
					writerFileListFinal.add(writerFileListSymbols.get(c2));
					out(writerFileListSymbols.get(c2));
					writerFileX+=writerFileListSymbols.get(c2);
					c2++;
				}
			}
		}

		else{
			out("Gene Symbol");
			c1=1;
			while (c1 < writerFileListWord.size() || c2 < writerFileListSymbols.size()) {

				if (c2 < writerFileListSymbols.size()) {
					writerFileListFinal.add(writerFileListSymbols.get(c2));
					writerFileX+=writerFileListSymbols.get(c2);
					c2++;
				}
				if (c1 < writerFileListWord.size()) {
					writerFileListFinal.add(writerFileListWord.get(c1));
					writerFileX+=writerFileListWord.get(c1);
					c1++;
				}

			}
		}

		try {
			PrintWriter writer = new PrintWriter("D:\\Compressed Folder Projects\\file02.txt", "UTF-8");
			writer.write(Arrays.toString(writerFileListFinal.toArray()));

			// writer.println("The second line");
			writer.close();
		} catch (Exception e) {

		}
		try {
//			PrintWriter writer = new PrintWriter("D:\\Compressed Folder Projects\\file3.txt", "UTF-8");
//			writer.write("Hello\nBAby");
//
//			// writer.println("The second line");
//			writer.close();
			File file = new File("D:\\Compressed Folder Projects\\file03.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			writerFileX=writerFileX.replaceAll("\n", "\r\n");
			bw.write(writerFileX);
			bw.close();

			System.out.println("Done");
		} catch (Exception e) {

		}

	}
	// return res;public static ArrayList<String> ;
	// if(writerFileListWord.size()>writerFileListSymbols.size())
	// {
	// for(String s:writerFileListWord)
	// {
	// writerFileListFinal.add(s);
	// }
	// }
	// else
	// {
	//
	// }

}
