package com.katari.examples.testmaker.service;

import com.katari.examples.testmaker.dto.UserAnswerDTO;
import com.katari.examples.testmaker.entity.UserAnswer;

public interface AnswerService {

	UserAnswer validateAnswer(UserAnswer userAnswer);
	
	UserAnswer saveAnswerWithChoicesInActiveExam(UserAnswerDTO userAnswer);
	
	UserAnswer findUserAnswerForActiveExamByQuestionSequence(Integer questionId);
}
