package com.training.anagramserver.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.training.anagramserver.util.AnagramProcessorUtil;

@Service
public class AnagramServiceImpl implements AnagramService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnagramServiceImpl.class);
	
    private ResourceLoader resourceLoader;
 
    @Inject
    public AnagramServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    @PostConstruct
    public void init(){
    	BufferedReader dictReader = null;
    	String word;
    	try {
            LOGGER.info("Trying to load anagram dictionary...");
 
            Resource resource = resourceLoader.getResource("classpath:dictionary.txt");
 
            dictReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
            
            while((word = dictReader.readLine())!= null){
            	AnagramProcessorUtil.processWord(word);
            }
            AnagramProcessorUtil.printStats();
            LOGGER.info("Anagram dictionary was loaded successfully.");
 
        } catch (IOException | NullPointerException e) {
            LOGGER.error("Anagram dictionary could not be initialized. ", e);
        } finally {
        	if (dictReader != null){
        		try {
					dictReader.close();
				} catch (IOException e) {
				}
        	}
        }
    }
	
	@Override
	public List<String> getAnagrams(String word) {
		return AnagramProcessorUtil.getAnagrams(word);
	}

}
