package com.katari.examples.testmaker.monitor;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.katari.examples.testmaker.entity.UserExam;
import com.katari.examples.testmaker.repository.UserExamRepository;
import com.katari.examples.testmaker.security.SecurityUtils;

@Aspect
@Component
public class BeforeExamServices {

	private final String usernameNotFound;
	private final String noActiveExamFound;
	private UserExamRepository userExamRepository;

	@Autowired
	public BeforeExamServices(@Value("${messages.user.name-not-found}") final String usernameNotFound,
			@Value("${messages.exam.no-active-found}") final String noActiveExamFound,
			UserExamRepository userExamRepository) {

		this.usernameNotFound = usernameNotFound;
		this.userExamRepository = userExamRepository;
		this.noActiveExamFound = noActiveExamFound;
	}

	@Before("execution(* com.katari.examples.testmaker.service..*Service.*ForUser*(..))")
	public void doUserCheck() {
		try {
			if (SecurityUtils.getUserName() == null) {
				throw new IllegalStateException(usernameNotFound);
			}
		} catch (NullPointerException ex) {
			throw new IllegalStateException(usernameNotFound);
		}
	}

	@Before("execution(* com.katari.examples.testmaker.service..*Service.*ActiveExam*(..))")
	public void doActiveExamCheck() {
		try {
			UserExam exam = userExamRepository.findActiveExamByUserName(SecurityUtils.getUserName());
			if (exam == null) {
				throw new IllegalStateException(noActiveExamFound);
			}
		} catch (NullPointerException ex) {
			throw new IllegalStateException(usernameNotFound);
		}
	}
}
