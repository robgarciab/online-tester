package com.katari.examples.testmaker.dto;

import java.util.ArrayList;
import java.util.List;

import com.katari.examples.testmaker.entity.QuestionChoice;
import com.katari.examples.testmaker.entity.UserAnswer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserAnswerDTO {

	private Integer questionSequence;
	private List<Long> selectedChoices;
	
	public static final UserAnswerDTO convertToDTO(UserAnswer userAnswer) {
		UserAnswerDTO userAnswerDTO = new UserAnswerDTO();
		userAnswerDTO.setQuestionSequence(userAnswer.getExamQuestion().getSequence());
		List<Long> selectedChoices = new ArrayList<Long>();
		for (QuestionChoice choice : userAnswer.getSelectedChoices()) {
			selectedChoices.add(choice.getId());
		}
		userAnswerDTO.setSelectedChoices(selectedChoices);
		return userAnswerDTO;
	}
}
