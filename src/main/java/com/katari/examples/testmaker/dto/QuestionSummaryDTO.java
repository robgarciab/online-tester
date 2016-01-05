package com.katari.examples.testmaker.dto;

import com.katari.examples.testmaker.entity.ExamQuestion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class QuestionSummaryDTO {

	private Integer sequence;
	private transient Boolean answered;
	
	public static final QuestionSummaryDTO convertToDTO(ExamQuestion questionEntity) {
		QuestionSummaryDTO questionDto =	new QuestionSummaryDTO();
		questionDto.setSequence(questionEntity.getSequence());
		return questionDto;
	}
}
