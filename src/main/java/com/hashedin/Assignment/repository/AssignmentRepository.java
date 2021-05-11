package com.hashedin.Assignment.repository;

import com.hashedin.Assignment.pojo.Netflix;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface AssignmentRepository extends JpaRepository<Netflix, String> {


}
