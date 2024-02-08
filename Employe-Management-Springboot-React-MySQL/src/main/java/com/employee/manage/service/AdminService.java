package com.employee.manage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.manage.entity.Admin;
import com.employee.manage.exception.UsernameOrIdAlreadyExistsException;
import com.employee.manage.repo.AdminRepo;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepo adminRepo;
	
	public List<Admin> getAlladmins(){
		
		return adminRepo.findAll();
	}
	
	public Admin createAdmin(Admin admin) {
		
		if(adminRepo.existsById(admin.getId())) {
			throw new UsernameOrIdAlreadyExistsException("Id already exists!!!");
		}
		
		return adminRepo.save(admin);
		
	}
	
	public boolean existsById(Long id) {
        return adminRepo.existsById(id);
    }
	
	public boolean existsByUsername(String userName) {
		return adminRepo.findByUsername(userName).isPresent();
		
	}

}
