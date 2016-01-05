/**
 * 
 */
package com.katari.examples.testmaker.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity that represents the Question assigned to a particular Exam
 * 
 * @author Roberto Garcia <robgarcia@gmail.com>
 *
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NamedQueries({
		@NamedQuery(name = "ExamQuestion.findExamQuestionsByExamId", query = "SELECT e FROM ExamQuestion e WHERE e.exam.id = :examId ORDER BY question.position"), })
public class ExamQuestion extends BaseEntity {

	private static final long serialVersionUID = 4688589782748771860L;
	
	@ManyToOne
	@JsonIgnore
	private Exam exam;
	@ManyToOne
	private Question question;
	private Integer sequence;
	private Double points;

	public ExamQuestion() {
	}
}
