package com.katari.examples.testmaker.service;

import java.util.List;

import com.katari.examples.testmaker.dto.QuestionSummaryDTO;
import com.katari.examples.testmaker.entity.ExamQuestion;

public interface QuestionService {

	List<QuestionSummaryDTO> findQuestionsSummaryForActiveExam();
	ExamQuestion findQuestionDetailsForActiveExamBySequence(Integer sequence);
}
