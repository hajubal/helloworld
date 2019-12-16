package com.ha.helloworld.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ha.helloworld.entity.Employee;
import com.ha.helloworld.repository.EmployeeRespository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class EmployeeServiceTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	EmployeeRespository repository;
	
	@Test
	public void getAllEmployees() {
		Employee entity = new Employee("first", "last", "mail");
		this.entityManager.persist(entity);
		this.entityManager.persist(entity);
		
		List<Employee> list = this.repository.findAll();
		
		assertThat(list.size()).isEqualTo(19);
	}
	
	public Employee getEmployeeById(Long id) {
		if(id == null) return null;
		
		Optional<Employee> option = this.repository.findById(id);
		
		if(option.isPresent()) return option.get();
		
		return null;
	}
	
	public Employee createOrUpdateEmployee(Employee entity) {
		
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
