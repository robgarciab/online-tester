package com.katari.examples.testmaker.repository;

import org.springframework.data.repository.CrudRepository;

import com.katari.examples.testmaker.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

}
