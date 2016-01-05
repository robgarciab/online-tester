/**
 * 
 */
package com.katari.examples.testmaker.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.katari.examples.testmaker.entity.emun.QuestionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity that represents the Question. Its independent from the Assessment
 * 
 * @author Roberto Garcia <robgarcia@gmail.com>
 *
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class Question extends BaseEntity {

	private static final long serialVersionUID = -9075663884394606737L;

	private Integer position;
	private String description;
	@Enumerated(EnumType.STRING)
	private QuestionType questionType;
	@OneToMany(mappedBy="question")
	@OrderBy("position ASC")
	private Set<QuestionChoice> choices;

	public Question() {
	}
}
