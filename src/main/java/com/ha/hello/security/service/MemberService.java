package com.ha.hello.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ha.helloworld.entity.Employee;
import com.ha.helloworld.entity.EmployeeDto;
import com.ha.helloworld.service.EmployeeService;

@Service
public class MemberService implements UserDetailsService {
	
	Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Resource(name = "EmployeeService")
	private EmployeeService employeeService;

	@Transactional
	public Long joinUser(EmployeeDto employeeDto) {
		logger.debug("EmployeeDto: {}", employeeDto.toString());
		
		// 비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
		
		return this.employeeService.createOrUpdateEmployee(employeeDto).getId();
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		logger.debug("loadUserByUsername: {}", id);
		
		Employee emp = this.employeeService.getEmployeeById(Long.parseLong(id));
		
		logger.debug("Emp: {}", emp);
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		GrantedAuthority auth = new SimpleGrantedAuthority("user");
		
		authorities.add(auth);
		
		UserDetails user = new User(id, emp.getPassword(), authorities);
		
		return user;
	}

}
