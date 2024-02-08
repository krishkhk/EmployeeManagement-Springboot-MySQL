package com.employee.manage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.manage.entity.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Long> {

}
