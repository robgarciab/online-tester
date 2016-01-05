/**
 * 
 */
package com.katari.examples.testmaker.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.katari.examples.testmaker.entity.emun.AnswerResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity that represents the Exam
 * 
 * @author Roberto Garcia <robgarcia@gmail.com>
 *
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserAnswer extends BaseEntity {

	private static final long serialVersionUID = 503932168066468651L;

	@JsonIgnore
	@ManyToOne
	private UserExam userExam;
	@JsonIgnore
	@ManyToOne
	private ExamQuestion examQuestion;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<QuestionChoice> selectedChoices;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private AnswerResult answerResult;

	public UserAnswer() {
	}
}
