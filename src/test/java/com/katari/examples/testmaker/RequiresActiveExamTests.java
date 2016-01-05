package com.katari.examples.testmaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import com.katari.examples.testmaker.service.ExamService;

public abstract class RequiresActiveExamTests extends RequiresLoggedUserTests {

	@Autowired
	private ExamService examService;

	protected final Long examId = 1l;
	
	@BeforeClass
	public final void startExam() {
		examService.startExamForUser(examId);
	}
}
