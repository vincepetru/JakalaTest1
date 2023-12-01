package com.Jakala.Test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Jakala.Test.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
	List<User> findByName(String name);
	List<User> findByUsertype(String userType);
	Optional<User>findById(int userId);
}
