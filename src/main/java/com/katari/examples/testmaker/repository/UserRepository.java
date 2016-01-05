package com.katari.examples.testmaker.repository;

import org.springframework.data.repository.CrudRepository;

import com.katari.examples.testmaker.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByName(String name);
}
