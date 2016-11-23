package com.nlpl.week1.CompressedProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.SliderUI;

public class WordPairTokenization {

	public static void main(String args[])
	{
		try
		{
			BufferedReader bf = new BufferedReader(new FileReader(
					"D:\\Compressed Folder Projects\\file1.txt"));
			out("File Exists");
			List<String> list2 = new ArrayList<String>();
			List<String> list3 = new ArrayList<String>();
			
			String fileText="",s;
			while ((s = bf.readLine()) != null) {
				// s=bf.readLine();
				fileText=fileText+s+"\n";
				

			}
			
//			String[] dictiona = list2.toArray(new String[0]);
//			System.out.println(Arrays.toString(dictiona));
			out(fileText);
			fileText=WordTokenization.tokenizationIssues(fileText);
			splitFile(fileText);
//			String newFileText="";
//			for(int i=0; i<fileText.length();i++)
//			{
//				String wordFromFiletext=fileText.charAt(i)+"";
//				if(!wordFromFiletext.matches("[A-Za-z]"))
//				{
//					newFileText=newFileText+"\n";
//				}
//				else
//				{
//					newFileText=newFileText+wordFromFiletext;
//				}
//				
//			}
//			out(newFileText);
					}
		catch(Exception e)
		{
			out(e.getMessage());
		}
	}
	public static void splitFile(String fileText)
	{
		String split[]=LowerCase(fileText).split("[^A-Za-z]+");
		split=WordTokenization.porterAlgo(split);
		
		out(Arrays.toString(split));
		final Map<String, Integer> counter = new HashMap<String, Integer>();
		String str;
		for (int i=0;i<split.length-1;i++)
		{
			str=split[i]+" "+split[i+1];
			
				counter.put(str, 1 + (counter.containsKey(str) ? counter.get(str) : 0));
			
		}
		List<String> list = new ArrayList<String>(counter.keySet());
		Collections.sort(list, new Comparator<String>() {
		    @Override
		    public int compare(String x, String y) {
		        return counter.get(y) - counter.get(x);
		    }
		});
		
		
		out(Arrays.toString(list.toArray(new String[list.size()])));
		for (String string : list) {
		    int frequency = counter.get(string);
		    System.out.println(string + ":" + frequency + ", ");
		}
		
		

		
	}
	public static void out(Object o)
	{
		System.out.println(o);
	}
	public static String LowerCase(String s)
	{
		return s.toLowerCase();
	}
//	public static String[] porterAlgo(String s[])
//	{
//		List<String> list = new ArrayList<String>();
//		for(String string :s)
//		{
//			list.add(porterWordLongerStemsStep3(porterWordLongerStems(porterWordEndingWithEd(porterWordEndingWithIng((porterAlgoShortterms(wordForContinousTense(string))))))));
//		}
//		return list.toArray(new String[0]);
//		}
//	public static String porterAlgoShortterms(String s)
//	{
//		if(s.endsWith("sses"))
//		{
//			return s.substring(0,s.length()-4)+"ss";
//		}
//		else if(s.endsWith("ies"))
//		{
//			return s.substring(0,s.length()-3)+"i";
//		}
//		else if(s.endsWith("ss"))
//		{
//			return s;
//		}
//		else if(s.endsWith("s"))
//		{
//			return s.substring(0,s.length()-1);
//		}
//		else
//		{
//			return s;
//		}
//	
//	}
//	public static String porterWordEndingWithIng(String s)
//	{
//		if(s.matches(".*[aeiou].*ing"))
//		{
//			return s.substring(0,s.length()-3);
//		}
//		else
//		{
//			return s;
//		}
//	}
//	public static String porterWordEndingWithEd(String s)
//	{
//		if(s.matches(".*[aeiou].*ed"))
//		{
//			
//			return s.substring(0,s.length()-2);
//		}
//		else
//		{
//			return s;
//		}
//	}
//	public static String porterWordLongerStems(String s)
//	{
//		if(s.endsWith("ational"))
//		{
//			return s.substring(0,s.length()-7)+"ate";
//		}
//		else if(s.endsWith("izer"))
//		{
//			return s.substring(0,s.length()-4)+"ize";
//		}
//		else if(s.endsWith("ator"))
//		{
//			return s.substring(0,s.length()-4)+"ate";
//		}
//		else
//		{
//			return s;
//		}
//	}
//	public static String porterWordLongerStemsStep3(String s)
//	{
//		if(s.endsWith("al"))
//		{
//			return s.substring(0,s.length()-2);
//		}
//		else if(s.endsWith("able"))
//		{
//			return s.substring(0,s.length()-4);
//		}
//		else if(s.endsWith("ate"))
//		{
//			return s.substring(0,s.length()-3);
//		}
//		else
//		{
//			return s;
//		}
//	}
//	public static String wordForContinousTense(String s)
//	{
//		if(s.equals("is") ||s.equals("am")||s.equals("are"))
//		{
//			return "be";
//		}
//		else
//		{
//			return s;
//		}
//	}
	
}
