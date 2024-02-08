package com.employee.manage.controller;


import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.employee.manage.entity.Employee;
import com.employee.manage.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService emplService;
	
	
	@GetMapping("/showList")
	public ResponseEntity<List<Employee>> getAllList() {
		List<Employee> employelist = emplService.getAllEmployeeList();
		return new ResponseEntity<>(employelist, HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getDepartmentId(@PathVariable Long id) {

		Optional<Employee> existEmploye = emplService.getEmployeeId(id);

		if (existEmploye.isPresent()) {
			return new ResponseEntity<>(existEmploye.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@PostMapping(value="/createEmployee",consumes = { "multipart/form-data" })
	public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee,
			@RequestParam("file") MultipartFile file) {

		Employee createEmployee;
		try {
			createEmployee = emplService.createEmploye(employee, file);
			return new ResponseEntity<>(createEmployee, HttpStatus.CREATED);
		} catch (IOException e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
		
		if (employee.getProfileImage() != null) {
            byte[] decodedImage = Base64.getDecoder().decode(employee.getProfileImage());
            employee.setProfileImage(decodedImage);
        }
		
		Optional<Employee> empl=emplService.updateEmployee(id, employee);
		return new ResponseEntity<Employee>(HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletDepartment(@PathVariable Long id){
		if(emplService.existsById(id)) {
			emplService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Employee Id " +id+" deleted Successfull ");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with Id " +id+ "Not Found !");
		}
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAllEmployeeList(){
		emplService.deleteAllEmployee();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("All employees deleted successfully");
	}
	
	
	

}
