package com.katari.examples.testmaker.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.katari.examples.testmaker.Application;
import com.katari.examples.testmaker.RequiresSavedAnswerTests;
import com.katari.examples.testmaker.entity.QuestionChoice;
import com.katari.examples.testmaker.entity.UserAnswer;

@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class UserAnswerRepositoryTests extends RequiresSavedAnswerTests {

	@Autowired
	private UserAnswerRepository userAnswerRepository;
	
	@Test
	public void testFindAnswersByUserExam() {
		List<UserAnswer> answers = userAnswerRepository.findByUserExamId(1l);
		for (UserAnswer answer : answers) {
			System.out.println("-----");
			System.out.println("ExamQuestionId:" + answer.getExamQuestion().getId() + " Choices:");
			for (QuestionChoice choice : answer.getSelectedChoices()) {
				System.out.println("ChoiceId:" + choice.getId());	
			}
		}
		assertEquals(answers.size(), 1);
	}
	
	@Test
	public void testFindByUserExam() {
		UserAnswer answer = userAnswerRepository.findByUserExamExamIdAndExamQuestionSequence(examId, questionSequence);
		assertNotNull(answer);
	}
}
