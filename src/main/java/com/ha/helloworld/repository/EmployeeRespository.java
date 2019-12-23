package com.ha.helloworld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ha.helloworld.entity.Employee;

public interface EmployeeRespository extends JpaRepository<Employee, Long>{
	
	List<Employee> findByFirstNameLike(String firstName);

	
	@Query("SELECT select * from tbl_employees where first_name like '%?1%'")
	List<Employee> selectDefineQuery(String firstName);
	
	//@Query("SELECT u.username FROM User u WHERE u.username LIKE CONCAT('%',:username,'%')")
	//List<String> findUsersWithPartOfName(@Param("username") String username);
}
