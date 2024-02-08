package com.employee.manage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.manage.entity.Department;


public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
