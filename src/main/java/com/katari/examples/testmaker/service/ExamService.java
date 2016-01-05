package com.katari.examples.testmaker.service;

import java.util.List;

import com.katari.examples.testmaker.entity.UserExam;

public interface ExamService {

	UserExam startExamForUser(Long examId) throws IllegalArgumentException, IllegalAccessError;
	
	Long getSecondsLeftForActiveExam() throws IllegalStateException;

	UserExam evaluateExamForUser(Long examId) throws IllegalArgumentException;
	
	UserExam findActiveExamForUser();
	
	UserExam findExamByIdForUser(Long examId);
	
	List<UserExam> findAvailableExamsForUser();
}
