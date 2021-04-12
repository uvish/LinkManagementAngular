package com.uvish.springApi.repository;

import com.uvish.springApi.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link,Long> {
}
