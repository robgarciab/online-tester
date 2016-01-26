/**
 * 
 */
package com.katari.examples.testmaker.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.katari.examples.testmaker.entity.User;
import com.katari.examples.testmaker.entity.UserAnswer;
import com.katari.examples.testmaker.entity.UserExam;
import com.katari.examples.testmaker.entity.emun.AnswerResult;
import com.katari.examples.testmaker.repository.UserExamRepository;
import com.katari.examples.testmaker.repository.UserRepository;
import com.katari.examples.testmaker.security.SecurityUtils;

/**
 * @author Roberto Garcia <robgarcia@gmail.com>
 *
 */

@Service
public class ExamServiceImpl implements ExamService {

	private final String notEnrolled;
	private final String examExpired;
	private final String activeExamFound;
	private final String forbidden;
	private UserRepository userRepository;
	private UserExamRepository userExamRepository;
	private AnswerService answerService;

	@Autowired
	public ExamServiceImpl(@Value("${messages.exam.not-enrolled}") final String notEnrolled,
			@Value("${messages.exam.expired}") final String examExpired,
			@Value("${messages.exam.active-found}") final String activeExamFound, 
			@Value("${messages.exam.no-active-found}") final String noActiveExam,
			@Value("${messages.exam.forbidden}") final String forbidden,
			UserRepository userRepository,
			UserExamRepository userExamRepository,
			AnswerService answerService) {

		this.notEnrolled = notEnrolled;
		this.examExpired = examExpired;
		this.activeExamFound = activeExamFound;
		this.userExamRepository = userExamRepository;
		this.userRepository = userRepository;
		this.answerService = answerService;
		this.forbidden = forbidden;
	}

	@Transactional
	@Override
	public UserExam startExamForUser(Long examId) {
		User user = userRepository.findByName(SecurityUtils.getUserName());
		UserExam exam = userExamRepository.findByExamIdAndUserName(examId, SecurityUtils.getUserName());
		// check if exam exists
		if (exam == null) {
			throw new IllegalArgumentException(notEnrolled);
		}
		// check if user has this exam assigned
		if (user == null || exam.getUser() == null || user.getId().compareTo(exam.getUser().getId()) != 0) {
			throw new IllegalAccessError(forbidden);
		}
		// check if exam has not expired
		Calendar cal = Calendar.getInstance();
		if (exam.getExpireDate().compareTo(cal.getTime()) < 0) {
			throw new IllegalArgumentException(examExpired);
		}
		// check if user has an active exam already
		UserExam activeExam = userExamRepository.findActiveExamByUserName(exam.getUser().getName());
		if (activeExam != null) {
			if (activeExam.getId().compareTo(exam.getId()) == 0) {
				return exam;
			} else {
				throw new IllegalArgumentException(activeExamFound);
			}
		}

		exam.setStartTime(cal.getTime());
		cal.add(Calendar.SECOND, exam.getTimeToComplete());
		exam.setEndTime(cal.getTime());
		return userExamRepository.save(exam);
	}

	@Transactional
	@Override
	public Long getSecondsLeftForActiveExam() {
		UserExam exam = userExamRepository.findActiveExamByUserName(SecurityUtils.getUserName());

		long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
		long endTimeInMillis = exam.getEndTime().getTime();
		if (currentTimeInMillis > endTimeInMillis) {
			exam.setSubmittedTime(Calendar.getInstance().getTime());
			return 0L;
		} else {
			return (endTimeInMillis - currentTimeInMillis) / 1000;
		}
	}

	@Transactional
	@Override
	public UserExam evaluateExamForUser(Long examId) {
		UserExam exam = findExamByIdForUser(examId);
		
		Calendar cal = Calendar.getInstance();
//		if (exam.getEndTime().compareTo(cal.getTime()) < 0) {
//			throw new IllegalArgumentException(timeOver);
//		}

		Double score = 0d;
		List<UserAnswer> answers = exam.getUserAnswers();

		for (UserAnswer answer : answers) {
			answer = answerService.validateAnswer(answer);
			if (answer.getAnswerResult() == AnswerResult.CORRECT) {
				score += answer.getExamQuestion().getPoints();
			}
		}
		exam.setScore(score);
		exam.setSubmittedTime(cal.getTime());
		return userExamRepository.save(exam);
	}
	
	@Override
	public UserExam findActiveExamForUser() {
		UserExam result = userExamRepository.findActiveExamByUserName(SecurityUtils.getUserName());
		return result;
	}

	@Override
	public List<UserExam> findAvailableExamsForUser() {
		List<UserExam> result = userExamRepository.findAvailableExamsByUserName(SecurityUtils.getUserName()); 
		return result;
	}

	@Override
	public UserExam findExamByIdForUser(Long examId) {
		return userExamRepository.findByExamIdAndUserName(examId, SecurityUtils.getUserName());
	}
}
