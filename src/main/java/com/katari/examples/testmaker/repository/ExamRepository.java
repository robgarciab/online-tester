package com.katari.examples.testmaker.repository;

import org.springframework.data.repository.CrudRepository;

import com.katari.examples.testmaker.entity.Exam;

public interface ExamRepository extends CrudRepository<Exam, Long> {

}
