package com.katari.examples.testmaker.service;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.katari.examples.testmaker.Application;

@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class ExamServiceNoUserLoggedTests extends AbstractTestNGSpringContextTests {

	@Value("${messages.user.name-not-found}")
	private String userNameNotFound;
	
	@Autowired
	private ExamService examService;
	
	@Test
	public void testStartExam() {
		try {
			examService.startExamForUser(1L);
		} catch (IllegalStateException ex) {
			assertEquals(ex.getMessage(), userNameNotFound);
		}
	}
}
