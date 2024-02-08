package com.employee.manage.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.employee.manage.entity.Admin;


public interface AdminRepo extends JpaRepository<Admin, Long> {
	
	Optional<Admin> findByUsername(String username);

}
