package com.ha.helloworld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ha.helloworld.entity.Employee;

public interface EmployeeRespository extends JpaRepository<Employee, Long>{
	
	List<Employee> findByFirstNameLike(String firstName);

	
	@Query(value="select * from tbl_employees where first_name like '%' || ?1 || '%'", nativeQuery = true)
	List<Employee> findByFirstNameLikeCustom(String firstName);
	
	@Query(value="select * from tbl_employees where first_name like concat('%', :firstName, '%')", nativeQuery = true)
	List<Employee> findByFirstNameLikeCustom2(@Param("firstName") String firstName);
	
	//@Query("SELECT u.username FROM User u WHERE u.username LIKE CONCAT('%',:username,'%')")
	//List<String> findUsersWithPartOfName(@Param("username") String username);
}
