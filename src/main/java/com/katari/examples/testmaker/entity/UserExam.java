/**
 * 
 */
package com.katari.examples.testmaker.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity that represents the Exam assigned to a particular User
 * 
 * @author Roberto Garcia <robgarcia@gmail.com>
 *
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NamedQueries({
		@NamedQuery(name = "UserExam.findAvailableExamsByUserName", query = "SELECT e"
				+ " FROM UserExam e"
				+ " WHERE e.user.name = :userName and e.expireDate > CURRENT_TIMESTAMP and e.submittedTime IS NULL and (e.endTime IS NULL or e.endTime > CURRENT_TIMESTAMP)"
				+ " ORDER BY expireDate ASC, startTime DESC"),
		@NamedQuery(name = "UserExam.findActiveExamByUserName", query = "SELECT e"
				+ " FROM UserExam e"
				+ " WHERE e.user.name = :userName and e.startTime IS NOT NULL and e.submittedTime IS NULL and e.endTime > CURRENT_TIMESTAMP"), })
public class UserExam extends BaseEntity {

	private static final long serialVersionUID = 3107291474757097906L;

	@JsonIgnore
	@ManyToOne
	private User user;
	@ManyToOne
	private Exam exam;
	@JsonIgnore
	@OneToMany(mappedBy = "userExam")
	private List<UserAnswer> userAnswers;
	@Temporal(TemporalType.TIMESTAMP)
	private Date expireDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedTime;
	// time in seconds
	private Integer timeToComplete;
	private Double score;

	public UserExam() {
	}
	
	@Override
	@JsonIgnore
	public Long getId() {
		return this.id;
	}
}
