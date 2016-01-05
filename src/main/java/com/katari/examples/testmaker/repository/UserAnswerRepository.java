package com.katari.examples.testmaker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.katari.examples.testmaker.entity.UserAnswer;

public interface UserAnswerRepository extends CrudRepository<UserAnswer, Long> {

	List<UserAnswer> findByUserExamId(Long userExamId);
	UserAnswer findByUserExamExamIdAndExamQuestionSequence(Long userExamId, Integer examQuestionSequence);
}
