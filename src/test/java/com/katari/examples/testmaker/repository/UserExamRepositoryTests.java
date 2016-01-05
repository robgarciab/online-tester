/**
 * 
 */
package com.katari.examples.testmaker.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.katari.examples.testmaker.Application;
import com.katari.examples.testmaker.RequiresActiveExamTests;
import com.katari.examples.testmaker.entity.UserExam;

/**
 * @author Roberto Garcia <robgarcia@gmail.com>
 *
 */

@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class UserExamRepositoryTests extends RequiresActiveExamTests {

	@Autowired
	private UserExamRepository userExamRepository;
	
	@Test
	public void testGetAvailableUserExams() {
		List<UserExam> exams = userExamRepository.findAvailableExamsByUserName("roberto");
		// 2 available Exams in import.sql 1 already started then only 1 must be available
		assertEquals(exams.size(), 2);
	}
	
	@Test
	public void testGetActiveExam() {
		UserExam exam = userExamRepository.findActiveExamByUserName("roberto");
		assertNotNull(exam);
	}
	
	@Test
	public void testFindByExamIdAndUserName() {
		UserExam exam = userExamRepository.findByExamIdAndUserName(1l, "roberto");
		assertNotNull(exam);
	}
}
