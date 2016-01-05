package com.katari.examples.testmaker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.katari.examples.testmaker.dto.QuestionSummaryDTO;
import com.katari.examples.testmaker.entity.ExamQuestion;
import com.katari.examples.testmaker.entity.UserAnswer;
import com.katari.examples.testmaker.entity.UserExam;
import com.katari.examples.testmaker.repository.ExamQuestionRepository;
import com.katari.examples.testmaker.repository.UserAnswerRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private ExamQuestionRepository examQuestionRepository;
	
	@Autowired
	private UserAnswerRepository userAnswerRepository;
	
	@Autowired
	private ExamService examService;
	
	@Override
	public List<QuestionSummaryDTO> findQuestionsSummaryForActiveExam() {
		UserExam userExam = examService.findActiveExamForUser();
		List<ExamQuestion> questions = examQuestionRepository.findByExamIdOrderByQuestionPosition(userExam.getExam().getId());
		List<UserAnswer> answers = userAnswerRepository.findByUserExamId(userExam.getId());
		List<Long> questionAnsweredIds = answers.stream().map(answer -> answer.getExamQuestion().getQuestion().getId()).collect(Collectors.toList());
		List<QuestionSummaryDTO> questionDtos = new ArrayList<QuestionSummaryDTO>();
		for (ExamQuestion question : questions) {
			QuestionSummaryDTO questionDTO = QuestionSummaryDTO.convertToDTO(question);
			if (questionAnsweredIds.contains(question.getId())) {
				questionDTO.setAnswered(true);
			} else {
				questionDTO.setAnswered(false);
			}
			questionDtos.add(questionDTO);
		}
		return questionDtos;
	}
	
	@Override
	public ExamQuestion findQuestionDetailsForActiveExamBySequence(Integer sequence) {
		UserExam userExam = examService.findActiveExamForUser();
		return examQuestionRepository.findByExamIdAndSequence(userExam.getExam().getId(), sequence);
	}
}
