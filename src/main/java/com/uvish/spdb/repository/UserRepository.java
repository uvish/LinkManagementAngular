package com.uvish.spdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uvish.spdb.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
