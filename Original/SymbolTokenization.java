package com.nlpl.week1.CompressedProject.Original;

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

public class SymbolTokenization {

	public static void main(String args[])
	{
		}
	public static void inputFile()
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
			splitFileSymbol(fileText);
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
	public static void splitFileSymbol(String fileText)
	{
		String split[]=LowerCase(fileText).split("[A-Za-z]+");
		
		
		out(Arrays.toString(split));
		final Map<String, Integer> counter = new HashMap<String, Integer>();
		for (String str : split)
		    counter.put(str, 1 + (counter.containsKey(str) ? counter.get(str) : 0));

		List<String> list = new ArrayList<String>(counter.keySet());
		Collections.sort(list, new Comparator<String>() {
		    @Override
		    public int compare(String x, String y) {
		        return counter.get(y) - counter.get(x);
		    }
		});
		
		
		out(Arrays.toString(list.toArray(new String[list.size()])));
		for (String str : list) {
		    int frequency = counter.get(str);
		    System.out.println(str + ":" + frequency + ", ");
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
	}
