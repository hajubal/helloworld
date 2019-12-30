package com.ha.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 메인 클래스
 * 
 * com.ha 패키지를 기준으로 annotation을 스캔한다. 지정하지 않은 경우 메인 클래스 하위의 annotation만 스캔한다.
 * 
 * @author hajubal
 *
 */
@SpringBootApplication(scanBasePackages = "com.ha")
public class HelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}
}
