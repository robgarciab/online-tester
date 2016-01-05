/**
 * 
 */
package com.katari.examples.testmaker.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Exam extends BaseEntity {

	private static final long serialVersionUID = -3366829111128572464L;
	
	private Double maxPoints;
	private Double minPointsToPass;
	private String description;
	private String instructions;
	
	@JsonIgnore
	@OneToMany(mappedBy="exam")
	@OrderBy("sequence ASC")
	private List<ExamQuestion> questions;
	
	public Exam() {
	}
}
