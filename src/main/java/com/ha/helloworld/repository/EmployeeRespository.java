package com.ha.helloworld.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ha.helloworld.entity.Employee;

public interface EmployeeRespository extends JpaRepository<Employee, Long>{

}
