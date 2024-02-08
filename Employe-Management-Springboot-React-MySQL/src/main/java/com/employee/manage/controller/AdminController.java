package com.employee.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.manage.entity.Admin;
import com.employee.manage.exception.UsernameOrIdAlreadyExistsException;
import com.employee.manage.service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {

		try {
			
			if (adminService.existsByUsername(admin.getUserName())) {
	            Admin error = new Admin();
	            error.setUserName("Error: Username already exists!!! Please use another username.");
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	        }
			
			Admin createAdmin = adminService.createAdmin(admin);
			return new ResponseEntity<>(createAdmin, HttpStatus.CREATED);
		} catch (UsernameOrIdAlreadyExistsException e) {
			Admin error = new Admin();
			error.setUserName("Error: User ID already exists!!! please use another");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

		}
		
		
	}
	
	@GetMapping("/list")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> getAllAdminList = adminService.getAlladmins();
        return new ResponseEntity<>(getAllAdminList, HttpStatus.OK);
    }
	
	
	
	

}
