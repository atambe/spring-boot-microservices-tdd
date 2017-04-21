package com.training.anagramserver.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnagramProcessorUtil {
	private static final Logger log = LoggerFactory.getLogger(AnagramProcessorUtil.class);
	private static HashMap<String, Set<String>> anagramMap = new HashMap<String, Set<String>>();

	public static void processWord(String word){
		if (word == null || word.equals("")){
			return;
		}
		String canonizedWord = canonizeString(word);
		
		Set<String> anagramList = anagramMap.get(canonizedWord);
		
		if (anagramList == null){
			// this is new entry in hashmap
			anagramList = new HashSet<String>();
			anagramList.add(word.toLowerCase());
			anagramMap.put(canonizedWord, anagramList);
		} else {
			
			// list should either be null or non-empty 
			// it should never be empty
			if (anagramList.isEmpty()){
				log.error("Encountered empty list for: " + word);
				throw new IllegalArgumentException("Dictionary structure corrupted");
			}
			
			// add to an existing entry
			anagramList.add(word);
		}
	}
	
	private static String canonizeString(String word){
		char[] chars = word.toUpperCase().toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
	
	public static List<String> getAnagrams(String word){
		String canonizedWord = canonizeString(word);
		
		Set<String> anagramSet = anagramMap.get(canonizedWord);
		
		return (anagramSet == null? new ArrayList<String>() : new ArrayList<String>(anagramSet));
	}
	
	public static void printStats(){
		log.debug("Found " + anagramMap.size() + " distinct anagrams in the dictionary");
	}
	
}
