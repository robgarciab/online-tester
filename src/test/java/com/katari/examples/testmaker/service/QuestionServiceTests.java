package com.katari.examples.testmaker.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.katari.examples.testmaker.Application;
import com.katari.examples.testmaker.RequiresSavedAnswerTests;
import com.katari.examples.testmaker.dto.QuestionSummaryDTO;
import com.katari.examples.testmaker.entity.ExamQuestion;

@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class QuestionServiceTests extends RequiresSavedAnswerTests {

	@Autowired
	private QuestionService questionService;
	
	@Test
	public void testFindQuestionsSummarySize() {
		List<QuestionSummaryDTO> questions = questionService.findQuestionsSummaryForActiveExam();
		assertEquals(questions.size(), 5);
	}
	
	@Test
	public void testFindQuestionsSummaryAnswered() {
		List<QuestionSummaryDTO> questions = questionService.findQuestionsSummaryForActiveExam();
		questions = questions.stream().filter(question -> question.getAnswered()).collect(Collectors.toList());
		assertEquals(questions.size(), 1);
	}
	
	@Test
	public void testFindQuestionDetailsForActiveExamBySequence() {
		ExamQuestion question = questionService.findQuestionDetailsForActiveExamBySequence(questionSequence);
		assertNotNull(question);
	}
}
