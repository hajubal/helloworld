package com.ha.hello.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ha.hello.security.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private MemberService memberService;
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().formLogin().defaultSuccessUrl("/employee");
//	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	//@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	logger.debug("AuthenticationManagerBuilder: {}", auth);
    	
        auth.userDetailsService(this.memberService).passwordEncoder(passwordEncoder());
    }
	
//	@Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider());
//    }
//
//	@Bean
//    public DaoAuthenticationProvider authProvider() throws Exception {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(memberService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			
	      .authorizeRequests()
	        .antMatchers("/", "/home", "/h2").permitAll()
	        .anyRequest().authenticated()
	        .and()
	      .formLogin()
	        .loginPage("/login")
	        .permitAll()
	        .and()
	      .logout()
	        .permitAll();	
	}

//	@Bean
//	  @Override
//	  public UserDetailsService userDetailsService() {
//	    UserDetails user =
//	       User.withDefaultPasswordEncoder()
//	        .username("user")
//	        .password("password")
//	        .roles("USER")
//	        .build();
//
//	    return new InMemoryUserDetailsManager(user);
//	  }
}
