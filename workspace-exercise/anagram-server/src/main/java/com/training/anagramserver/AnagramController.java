package com.training.anagramserver;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.anagramserver.service.AnagramService;


@RestController
public class AnagramController {
	
	@Inject
	AnagramService anagramService;

	@RequestMapping(path = "/anagrams", method = RequestMethod.GET)
	public ResponseEntity<List<String>> checkAnagrams(@RequestParam ("word") String word){ 
		List<String> anagramList = anagramService.getAnagrams(word);
		return new ResponseEntity<List<String>>(anagramList, HttpStatus.OK);
	}
}
