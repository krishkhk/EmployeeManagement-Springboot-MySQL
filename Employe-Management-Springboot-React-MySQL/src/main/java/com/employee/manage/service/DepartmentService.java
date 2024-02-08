package com.employee.manage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.manage.entity.Department;
import com.employee.manage.repo.DepartmentRepo;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepo departmentRepo;

	public List<Department> getAllDepartments() {

		return departmentRepo.findAll();
	}

	public Optional<Department> getDepartmentById(Long id) {

		return departmentRepo.findById(id);
	}

	public Department createDepartment(Department department) {
		return departmentRepo.save(department);
	}

	
	public Optional<Department> updateDepartment(Long id,Department department){
		Optional<Department> existingDepartment=departmentRepo.findById(id);
		if(existingDepartment.isPresent()) {
			department.setId(id);
			return Optional.of(departmentRepo.save(department));
		}else {
			return Optional.empty();
		}
	}
	

	public void deleteDepartments(Long id) {
		departmentRepo.deleteById(id);
	}
	
	

}
