package com.employee.manage.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.employee.manage.entity.Employee;
import com.employee.manage.exception.UsernameOrIdAlreadyExistsException;
import com.employee.manage.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	 public boolean existsById(Long id) {
	        return employeeRepo.existsById(id);
	    }
	
	public List<Employee> getAllEmployeeList(){
		return employeeRepo.findAll();
	}
	
	public Optional<Employee> getEmployeeId(Long id) {
		return employeeRepo.findById(id);
	}
	
	
	public Employee createEmploye(Employee employee,MultipartFile file) throws IOException{
		
		if(file!=null&&!file.isEmpty()) {
			employee.setProfileImage(file.getBytes());
		}
		
		if(employeeRepo.existsById(employee.getId())) {
			throw new UsernameOrIdAlreadyExistsException("Id already exists !!");
		}
		return employeeRepo.save(employee);
		
	}
	
	public Optional<Employee> updateEmployee(Long id,Employee employee){
		
		Optional<Employee> existEmployeId =employeeRepo.findById(id);
		
		if(existEmployeId.isPresent()) {
			employee.setId(id);
			return Optional.of(employeeRepo.save(employee));
		}else {
			return Optional.empty();
		}
		
	}
	
	
	public void deleteById(Long id) {
		employeeRepo.deleteById(id);
	}
	
	public void deleteAllEmployee() {
		employeeRepo.deleteAll();
	}
	
	
	
	
	
	
	

}
