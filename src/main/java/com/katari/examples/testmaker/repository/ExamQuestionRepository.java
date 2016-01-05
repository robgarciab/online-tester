package com.katari.examples.testmaker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.katari.examples.testmaker.entity.ExamQuestion;

public interface ExamQuestionRepository extends CrudRepository<ExamQuestion, Long> {

	List<ExamQuestion> findByExamIdOrderByQuestionPosition(Long examId);
	ExamQuestion findByExamIdAndSequence(Long examId, Integer sequence);
}
