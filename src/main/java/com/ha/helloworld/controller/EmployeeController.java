package com.ha.helloworld.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ha.helloworld.entity.Employee;
import com.ha.helloworld.exception.RecordNotFoundException;
import com.ha.helloworld.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> list = this.employeeService.getAllEmployees();
		
		logger.info("Result : {}", list);
		
		ResponseEntity<List<Employee>> response = new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee entity = employeeService.getEmployeeById(id);
        logger.debug("", "");
        if(entity == null) {
        	throw new RecordNotFoundException("Invalid employee id: " + id);
        }
 
        return new ResponseEntity<Employee>(entity, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/error")
    public ResponseEntity<Employee> error() {
		
		String a = null;
		
		a.charAt(2);
        
        return ResponseEntity.ok().build();
 
    }
 
    @PostMapping
    public ResponseEntity<Employee> createOrUpdateEmployee(Employee employee) {
        Employee updated = employeeService.createOrUpdateEmployee(employee);
        return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeById(@PathVariable("id") Long id) {
    	employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }
}
