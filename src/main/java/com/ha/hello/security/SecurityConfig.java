package com.ha.hello.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.ha.hello.security.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private MemberService memberService;
//	
//	@Override
//	public void configure(WebSecurity web) {
//		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().formLogin().defaultSuccessUrl("/employee");
//	}
//	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//	
//	@Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.memberService).passwordEncoder(passwordEncoder());
//    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	      .authorizeRequests()
	        .antMatchers("/", "/home").permitAll()
	        .anyRequest().authenticated()
	        .and()
	      .formLogin()
	        .loginPage("/login")
	        .permitAll()
	        .and()
	      .logout()
	        .permitAll();	
	}

	@Bean
	  @Override
	  public UserDetailsService userDetailsService() {
	    UserDetails user =
	       User.withDefaultPasswordEncoder()
	        .username("user")
	        .password("password")
	        .roles("USER")
	        .build();

	    return new InMemoryUserDetailsManager(user);
	  }
}
