package com.katari.examples.testmaker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.katari.examples.testmaker.entity.QuestionChoice;

public interface QuestionChoiceRepository extends CrudRepository<QuestionChoice, Long> {

	List<QuestionChoice> findByQuestionIdOrderByPosition(Long QuestionId);
}
