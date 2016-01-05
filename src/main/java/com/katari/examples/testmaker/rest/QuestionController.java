package com.katari.examples.testmaker.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katari.examples.testmaker.dto.QuestionSummaryDTO;
import com.katari.examples.testmaker.entity.ExamQuestion;
import com.katari.examples.testmaker.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(method=GET)
	public List<QuestionSummaryDTO> findQuestionsSummaryForActiveExam() {
		return questionService.findQuestionsSummaryForActiveExam();
	}
	
	@RequestMapping(value="/{sequence}", method=GET)
	public ExamQuestion getQuestionBySequence(@PathVariable("sequence") Integer id) {
		return questionService.findQuestionDetailsForActiveExamBySequence(id);
	}
}
