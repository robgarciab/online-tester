package com.katari.examples.testmaker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import com.katari.examples.testmaker.dto.UserAnswerDTO;
import com.katari.examples.testmaker.service.AnswerService;

public abstract class RequiresSavedAnswerTests extends RequiresActiveExamTests {

	@Autowired
	private AnswerService answerService;
	
	protected final Integer questionSequence = 3;
	protected final Long questionChoiceId = 12l;
	
	@BeforeClass
	public void saveAnswer() {
		UserAnswerDTO userAnswerDTO = setAnswerFields(questionSequence, questionChoiceId);
		System.out.println("saving answer: questionSequence ->" + questionSequence + " questionChoiceId ->" + questionChoiceId);
		answerService.saveAnswerWithChoicesInActiveExam(userAnswerDTO);
	}
	
	protected UserAnswerDTO setAnswerFields(Integer questionSequence, Long questionChoiceId) {
		UserAnswerDTO userAnswer = new UserAnswerDTO();
		userAnswer.setQuestionSequence(questionSequence);
		List<Long> selectedChoices = new ArrayList<Long>();
		selectedChoices.add(questionChoiceId);
		userAnswer.setSelectedChoices(selectedChoices);
		return userAnswer;
	}
}
