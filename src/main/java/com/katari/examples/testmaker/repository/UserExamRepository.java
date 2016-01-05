package com.katari.examples.testmaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.katari.examples.testmaker.entity.UserExam;

/**
 * @author robgarciab
 *
 */
public interface UserExamRepository extends JpaRepository<UserExam, Long>, CrudRepository<UserExam, Long> {

	UserExam findByExamIdAndUserName(@Param("examId") Long examId, @Param("userName") String userName);
	
	UserExam findActiveExamByUserName(@Param("userName") String userName);
	
	List<UserExam> findAvailableExamsByUserName(@Param("userName") String userName);
}
