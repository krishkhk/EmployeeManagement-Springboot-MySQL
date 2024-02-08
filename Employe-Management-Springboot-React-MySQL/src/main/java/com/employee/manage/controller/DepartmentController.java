package com.employee.manage.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.employee.manage.entity.Department;
import com.employee.manage.service.DepartmentService;

@RestController
@RequestMapping("/Department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departService;
	
	
	@GetMapping("/showList")
	public ResponseEntity<List<Department>> getAllDepartments(){
		
		List<Department>departments=departService.getAllDepartments();
		return new ResponseEntity<>(departments,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department>getDepartmentId(@PathVariable Long id){
		
		Optional<Department>department=departService.getDepartmentById(id);
		
	if(department.isPresent()) {
		return new ResponseEntity<>(department.get(),HttpStatus.OK);
	}else {
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		
		
	}
	
	@PostMapping("/createDepartment")
	public ResponseEntity<Department> createDepartmentList(@RequestBody Department department){
		
		Department createDepo=departService.createDepartment(department);
		return new ResponseEntity<>(createDepo,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartmentList(@PathVariable Long id,@RequestBody Department department){
		Optional<Department> updateDepo=departService.updateDepartment(id, department);
		return new ResponseEntity<Department>(HttpStatus.OK);
		
	}
	
	 @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
		 departService.deleteDepartments(id);
 
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Department with ID " + id + " deleted successfully");
	    }
	
	

	
	
	

}
