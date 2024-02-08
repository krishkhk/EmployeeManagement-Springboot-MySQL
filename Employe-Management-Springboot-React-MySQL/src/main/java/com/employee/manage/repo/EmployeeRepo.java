package com.employee.manage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.manage.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
