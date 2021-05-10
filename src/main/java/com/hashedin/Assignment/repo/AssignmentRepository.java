package com.hashedin.Assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

import com.hashedin.Assignment.netflixDetails.NetflixRecords;


public interface AssignmentRepository extends JpaRepository<NetflixRecords, String> {

}
