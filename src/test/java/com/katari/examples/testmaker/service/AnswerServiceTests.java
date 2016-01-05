/**
 * 
 */
package com.katari.examples.testmaker.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.katari.examples.testmaker.Application;
import com.katari.examples.testmaker.RequiresSavedAnswerTests;
import com.katari.examples.testmaker.dto.UserAnswerDTO;
import com.katari.examples.testmaker.entity.UserAnswer;
import com.katari.examples.testmaker.entity.emun.AnswerResult;

/**
 * @author Roberto Garcia <robgarcia@gmail.com>
 *
 */

@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class AnswerServiceTests extends RequiresSavedAnswerTests {

	@Autowired
	private AnswerService answerService;
	
	@Test
	@Transactional
	public void testSaveNewAnswer() {
		UserAnswerDTO userAnswerDTO = setAnswerFields(4, 17l);
		UserAnswer userAnswer = answerService.saveAnswerWithChoicesInActiveExam(userAnswerDTO);
		assertNotNull(userAnswer);
	}
	
	@Test
	@Transactional
	public void testSaveExistingAnswer() {
		UserAnswerDTO userAnswerDTO = setAnswerFields(questionSequence, 14l);
		UserAnswer userAnswer = answerService.saveAnswerWithChoicesInActiveExam(userAnswerDTO);
		assertNotNull(userAnswer);
	}
	
	@Test
	@Transactional
	public void testValidateCorrectAnswer() {
		UserAnswerDTO userAnswerDTO = setAnswerFields(1, 4l);
		UserAnswer userAnswer = answerService.saveAnswerWithChoicesInActiveExam(userAnswerDTO);
		userAnswer = answerService.validateAnswer(userAnswer);
		assertEquals(userAnswer.getAnswerResult(), AnswerResult.CORRECT);
	}

	@Test
	@Transactional
	public void testValidateWrongAnswer() {
		UserAnswerDTO userAnswerDTO = setAnswerFields(2, 8l);
		UserAnswer userAnswer = answerService.saveAnswerWithChoicesInActiveExam(userAnswerDTO);
		userAnswer = answerService.validateAnswer(userAnswer);
		assertEquals(userAnswer.getAnswerResult(), AnswerResult.WRONG);
	}
}
