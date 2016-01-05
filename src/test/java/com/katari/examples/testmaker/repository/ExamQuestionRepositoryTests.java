package com.katari.examples.testmaker.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.katari.examples.testmaker.Application;
import com.katari.examples.testmaker.entity.ExamQuestion;

@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class ExamQuestionRepositoryTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private ExamQuestionRepository examQuestionRepository;
	
	@Test
	public void testGetExamQuestions() {
		List<ExamQuestion> questions = examQuestionRepository.findByExamIdOrderByQuestionPosition(1L);
		// Exam 1 in import.sql has 5 questions
		assertEquals(questions.size(),5);
	}
	
	@Test
	public void testGetExamQuestionBySequence() {
		ExamQuestion question = examQuestionRepository.findByExamIdAndSequence(1L, 1);
		assertNotNull(question);
	}
}
