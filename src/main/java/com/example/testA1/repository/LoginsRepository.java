package com.example.testA1.repository;

import com.example.testA1.model.Logins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginsRepository extends JpaRepository<Logins, Integer> {
}
