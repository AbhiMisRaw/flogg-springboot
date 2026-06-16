package com.abhi.flogg.repository;

import com.abhi.flogg.entity.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Override
    Optional<Blog> findById(Long aLong);

    @Override
    List<Blog> findAll();
}