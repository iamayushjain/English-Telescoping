package com.nlpl.week1.CompressedProject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Iterator;

import java.util.List;
import java.util.Map;

public class ShannonFano {

    public static LinkedHashMap compress(LinkedHashMap freq) {
		
		LinkedHashMap<String, StringBuilder> result = new LinkedHashMap<String, StringBuilder>();
		List<String> charList = new ArrayList<String>();
		List<Double> probabilty = new ArrayList<Double>();
		
		Iterator entries = freq.entrySet().iterator();
		while( entries.hasNext() ) {
			Map.Entry<String, Double> entry = (Map.Entry)entries.next();
			charList.add(entry.getKey());
			probabilty.add(entry.getValue());
			
		}
		PairOrWordSelection.out(result.toString());
		PairOrWordSelection.out(charList.toString());
		PairOrWordSelection.out(probabilty.toString());
		addBit(result, charList,probabilty, true,.5);
		PairOrWordSelection.out(result.toString());
		return result;
	}
	
	private static void addBit(LinkedHashMap<String, StringBuilder> result, List<String> charList, List<Double> probList, boolean up,double probScore) {
		String bit = "";
		if( !result.isEmpty() ) {
			bit = (up) ? ("0") :("1");
		}
		
		for( String c : charList ) {
			StringBuilder s =  ((result.get(c) == null) ? new StringBuilder("") : result.get(c));
			result.put(c, s.append(bit));
		}
		int separator=0;
		double probTotal=0;
		try
		{
		while(probTotal<=probScore)
		{
			probTotal+=probList.get(separator);
			separator++;
		}
		}
		catch(Exception e)
		{
			
		}
		if( charList.size() > 2 ) {
			
			
			List<String> upList = charList.subList(0, separator);
			List<Double> upListProb = probList.subList(0, separator);
			addBit(result, upList,upListProb,true,probTotal/2);
			List<String> downList = charList.subList(separator, charList.size());
			List<Double> downListProb = probList.subList(separator, charList.size());
			addBit(result, downList,downListProb ,false,(probScore*2-probTotal)/2);
		}
		else if( charList.size() == 2 ) {
			
			
			List<String> upList = charList.subList(0, 1);
			List<Double> upListProb = probList.subList(0, 1);
			addBit(result, upList,upListProb,true,probTotal/2);
			List<String> downList = charList.subList(1, charList.size());
			List<Double> downListProb = probList.subList(1, charList.size());
			addBit(result, downList,downListProb ,false,(probScore*2-probTotal)/2);
		}
		 
			
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final LinkedHashMap<String, Double> counter = new LinkedHashMap<String, Double>();
		counter.put("a",.3);
		
		counter.put("b",.25);
		counter.put("c",.2);
		counter.put("d",.12);
		counter.put("e",.08);
		counter.put("f",.05);
		PairOrWordSelection.out(counter.toString());
		
		PairOrWordSelection.out(compress(counter).toString());
	}

}
