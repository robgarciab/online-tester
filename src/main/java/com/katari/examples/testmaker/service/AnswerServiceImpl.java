/**
 * 
 */
package com.katari.examples.testmaker.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.katari.examples.testmaker.dto.UserAnswerDTO;
import com.katari.examples.testmaker.entity.ExamQuestion;
import com.katari.examples.testmaker.entity.Question;
import com.katari.examples.testmaker.entity.QuestionChoice;
import com.katari.examples.testmaker.entity.UserAnswer;
import com.katari.examples.testmaker.entity.UserExam;
import com.katari.examples.testmaker.entity.emun.AnswerResult;
import com.katari.examples.testmaker.repository.ExamQuestionRepository;
import com.katari.examples.testmaker.repository.QuestionChoiceRepository;
import com.katari.examples.testmaker.repository.UserAnswerRepository;
import com.katari.examples.testmaker.repository.UserExamRepository;
import com.katari.examples.testmaker.security.SecurityUtils;

/**
 * @author Roberto Garcia <robgarcia@gmail.com>
 *
 */

@Service
public class AnswerServiceImpl implements AnswerService {

	private QuestionChoiceRepository questionChoiceRepository;
	private UserExamRepository userExamRepository;
	private ExamQuestionRepository examQuestionRepository;
	private UserAnswerRepository userAnswerRepository;

	private final String noChoiceSelected;
	
	@Autowired
	public AnswerServiceImpl(@Value("${messages.answer.edit.not-choice-selected}") final String noChoiceSelected,
			QuestionChoiceRepository questionChoiceRepository,
			UserExamRepository userExamRepository,
			ExamQuestionRepository examQuestionRepository, 
			UserAnswerRepository userAnswerRepository) {

		this.questionChoiceRepository = questionChoiceRepository;
		this.examQuestionRepository = examQuestionRepository;
		this.userAnswerRepository = userAnswerRepository;
		this.userExamRepository = userExamRepository;
		this.noChoiceSelected = noChoiceSelected;
	}
	
	@Transactional
	public UserAnswer validateAnswer(UserAnswer userAnswer) {
		ExamQuestion examQuestion = userAnswer.getExamQuestion();
		Question question = examQuestion.getQuestion();
		Set<QuestionChoice> userSelection = userAnswer.getSelectedChoices();
		Set<QuestionChoice> correctSelection = question.getChoices().stream().filter(choice -> choice.getCorrect()).collect(Collectors.toSet());
		if (correctSelection.containsAll(userSelection) && userSelection.size() == correctSelection.size()) {
			userAnswer.setAnswerResult(AnswerResult.CORRECT);
		} else {
			userAnswer.setAnswerResult(AnswerResult.WRONG);
		}
		return userAnswerRepository.save(userAnswer);
	}
	
	@Transactional
	public UserAnswer saveAnswerWithChoicesInActiveExam(UserAnswerDTO userAnswerDTO) {
		UserExam userExam = userExamRepository.findActiveExamByUserName(SecurityUtils.getUserName());
		UserAnswer userAnswer = userAnswerRepository.findByUserExamExamIdAndExamQuestionSequence(userExam.getId(), userAnswerDTO.getQuestionSequence());
		
		if (userAnswer == null) {
			userAnswer = new UserAnswer();
			userAnswer.setUserExam(userExamRepository.findOne(userExam.getId()));
			userAnswer.setExamQuestion(examQuestionRepository.findByExamIdAndSequence(userExam.getExam().getId(), userAnswerDTO.getQuestionSequence()));
		}
		
		// get new selected choices
		Set<QuestionChoice> selectedChoices = new HashSet<QuestionChoice>();
		if (userAnswerDTO.getSelectedChoices() == null) {
			throw new IllegalArgumentException(noChoiceSelected);
		}
		for (Long choiceId : userAnswerDTO.getSelectedChoices()) {
			selectedChoices.add(questionChoiceRepository.findOne(choiceId));
		}
		userAnswer.setSelectedChoices(selectedChoices);
		return userAnswerRepository.save(userAnswer);
	}

	@Override
	public UserAnswer findUserAnswerForActiveExamByQuestionSequence(Integer sequence) {
		UserExam exam = userExamRepository.findActiveExamByUserName(SecurityUtils.getUserName());
		UserAnswer answer = userAnswerRepository.findByUserExamExamIdAndExamQuestionSequence(exam.getId(), sequence);
		return answer;
	}
}
