package com.katari.examples.testmaker.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katari.examples.testmaker.entity.UserExam;
import com.katari.examples.testmaker.service.ExamService;

@RestController
@RequestMapping("/exams")
public class ExamController {

	@Autowired
	private ExamService examService;

	@RequestMapping(value="/active", method=GET)
	public UserExam getActiveExam() {
		return examService.findActiveExamForUser();
	}
	
	@RequestMapping(value="/{id}", method=GET)
	public UserExam getExamDetails(@PathVariable("id") Long examId) {
		return examService.findExamByIdForUser(examId);
	}
	
	@RequestMapping(value="/availables", method=GET)
	public List<UserExam> getAvailableExams() {
		return examService.findAvailableExamsForUser();
	}
	
	@RequestMapping(value="/start/{id}", method=PUT)
	public UserExam startExam(@PathVariable("id") Long userExamId) {
		return examService.startExamForUser(userExamId);
	}

	@RequestMapping(value="/{id}/evaluate", method=PUT)
	public UserExam evaluateExam(@PathVariable("id") Long examId) {
		return examService.evaluateExamForUser(examId);
	}

	@RequestMapping(value="/active/secondsLeft", method=GET)
	public Long getSecondsLeftForActiveExam() {
		return examService.getSecondsLeftForActiveExam();
	}
}
