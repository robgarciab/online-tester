/**
 * 
 */
package com.katari.examples.testmaker.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.katari.examples.testmaker.Application;
import com.katari.examples.testmaker.RequiresActiveExamTests;
import com.katari.examples.testmaker.entity.ExamQuestion;
import com.katari.examples.testmaker.entity.QuestionChoice;
import com.katari.examples.testmaker.entity.UserAnswer;
import com.katari.examples.testmaker.entity.UserExam;
import com.katari.examples.testmaker.repository.QuestionChoiceRepository;
import com.katari.examples.testmaker.repository.UserAnswerRepository;
import com.katari.examples.testmaker.repository.UserExamRepository;

/**
 * @author Roberto Garcia <robgarcia@gmail.com>
 *
 */

@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class ExamServiceTests extends RequiresActiveExamTests {

	@Value("${messages.exam.expired}")
	private String examExpired;
	
	@Value("${messages.exam.not-enrolled}")
	private String notEnrolled;
	
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private UserAnswerRepository userAnswerRepository;
	
	@Autowired
	private UserExamRepository userExamRepository;
	
	@Autowired
	private QuestionChoiceRepository questionChoiceRepository;
	
	@Test
	public void testStartExpiredExam() {
		try {
			examService.startExamForUser(3l);
		} catch (IllegalArgumentException ex) {
			assertEquals(ex.getMessage(), examExpired);
		}
	}
	
	@Test
	public void testStartNotEnrolledExam() {
		try {
			examService.startExamForUser(4l);
		} catch (IllegalArgumentException ex) {
			assertEquals(ex.getMessage(), notEnrolled);
		}
	}

	@Test
	@Transactional
	public void testEvaluateExam() {
		UserExam userExam = userExamRepository.findActiveExamByUserName("roberto");

		int questionIndex = 0;
		for (ExamQuestion examQuestion : userExam.getExam().getQuestions()) {
			Set<QuestionChoice> selectedChoices = new HashSet<QuestionChoice>();
			Long questionChoiceId = null;
			switch (questionIndex++) {
			case 0:
				// Question 1 CORRECT
				questionChoiceId = 4L;
				break;
			case 1:
				// Question 2 CORRECT
				questionChoiceId = 7L;
				break;
			case 2:
				// Question 3 CORRECT
				questionChoiceId = 15L;
				break;
			case 3:
				// Question 4 CORRECT
				questionChoiceId = 16L;
				break;
			case 4:
				// Question 5 WRONG, CORRECT ANSWER is 25
				questionChoiceId = 24L;
				break;
			}
			QuestionChoice questionChoice = questionChoiceRepository.findOne(questionChoiceId);

			selectedChoices.add(questionChoice);
			userAnswerRepository.save(new UserAnswer(userExam, examQuestion, selectedChoices, null));
		}

		// Evaluate exam
		userExam = examService.evaluateExamForUser(1l);
		assertEquals(userExam.getScore(), 8.0);
	}

	@Test
	public void testSecondsLeftForExam() {
		Long secondsLeft = examService.getSecondsLeftForActiveExam();
		assertTrue(secondsLeft > 0L);
	}
	
	@Test
	public void testFindByExamIdForUser() {
		UserExam exam = examService.findExamByIdForUser(1l);
		assertNotNull(exam);
	}
}
