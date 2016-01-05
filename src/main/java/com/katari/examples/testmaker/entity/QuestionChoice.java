/**
 * 
 */
package com.katari.examples.testmaker.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity that represents the Choice a Use can select within a Question
 * 
 * @author Roberto Garcia <robgarcia@gmail.com>
 *
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = false, exclude = "question")
@AllArgsConstructor
public class QuestionChoice extends BaseEntity {

	private static final long serialVersionUID = -2819076283458126783L;

	@JsonIgnore
	@ManyToOne
	private Question question;
	private Integer position;
	private String description;
	@JsonIgnore
	private Boolean correct;

	public QuestionChoice() {
	}
}
