package com.katari.examples.testmaker.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.katari.examples.testmaker.dto.UserAnswerDTO;
import com.katari.examples.testmaker.entity.UserAnswer;
import com.katari.examples.testmaker.service.AnswerService;

@RestController
@RequestMapping("/answers")
public class AnswerController {
		
	@Autowired
	private AnswerService answerService;
	
	@RequestMapping(method=GET)
	public UserAnswerDTO findUserAnswerForActiveExamByQuestionId(@RequestParam("examQuestionSequence") Integer examQuestionSequence) {
		UserAnswer answer = answerService.findUserAnswerForActiveExamByQuestionSequence(examQuestionSequence);
		return answer != null ? UserAnswerDTO.convertToDTO(answer) : null;
	}
	
	@RequestMapping(method=PUT)
	public UserAnswerDTO saveUserAnswer(@RequestBody UserAnswerDTO userAnswer) {
		UserAnswer answer = answerService.saveAnswerWithChoicesInActiveExam(userAnswer);
		return UserAnswerDTO.convertToDTO(answer);
	}
}
