package com.unit_test_exercise.repositories;

import com.unit_test_exercise.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends JpaRepository<User,Long> {
}
