package com.katari.examples.testmaker.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.katari.examples.testmaker.entity.QuestionChoice;
import com.katari.examples.testmaker.repository.QuestionChoiceRepository;

@RestController
@RequestMapping("/choices")
public class ChoiceController {

	@Autowired
	private QuestionChoiceRepository questionChoiceRepository;
	
	@RequestMapping(method=GET)
	public List<QuestionChoice> getQuestionChoicesByQuestionId(@RequestParam("questionId") Long questionId) {
		return questionChoiceRepository.findByQuestionIdOrderByPosition(questionId);
	}
}