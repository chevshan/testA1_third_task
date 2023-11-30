package com.example.testA1.repository;

import com.example.testA1.model.Postings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingsRepository extends JpaRepository<Postings, Integer> {
}
