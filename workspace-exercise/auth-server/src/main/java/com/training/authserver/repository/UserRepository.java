package com.training.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.training.authserver.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User getUserByName(String name);
		
	@Transactional
	public void deleteUserByName(String name);

}
