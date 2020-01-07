package com.ha.hello.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfigTest {

	@Test
	public void password() {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		
		System.out.println(enc.encode("1"));
	}
}
