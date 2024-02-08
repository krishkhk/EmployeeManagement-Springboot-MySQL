package com.employee.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.employee.manage.entity.Manager;
import com.employee.manage.repo.ManagerRepo;

public class MangerService {
	
	@Autowired
	private ManagerRepo managerRepo;
	
	public List<Manager> getAllManger(){
		return managerRepo.findAll();
	}

}
