package com.ha.helloworld.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ha.helloworld.entity.Employee;
import com.ha.helloworld.entity.EmployeeDto;
import com.ha.helloworld.repository.EmployeeRespository;

@Service(value = "EmployeeService")
public class EmployeeService {

	@Autowired
	EmployeeRespository repository;
	
	public List<Employee> getAllEmployees() {
		List<Employee> list = this.repository.findAll();
		
		if(list == null) return new ArrayList<Employee>();
		
		return list;
	}
	
	public Employee getEmployeeById(Long id) {
		if(id == null) return null;
		
		Optional<Employee> option = this.repository.findById(id);
		
		if(option.isPresent()) return option.get();
		
		return null;
	}
	
	public Employee createOrUpdateEmployee(EmployeeDto entity) {
		
		Employee data = this.getEmployeeById(entity.getId());
		
		if(data == null) data = new Employee();
		
		data.setFirstName(entity.getFirstName());
		data.setLastName(entity.getLastName());
		data.setEmail(entity.getEmail());
		
		this.repository.save(data);
		
		return data;
	}
	
	public void deleteEmployeeById(Long id) {
		this.repository.deleteById(id);
	}
}
