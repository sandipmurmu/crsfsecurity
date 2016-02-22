package com.security.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@Profile("session")
@Configuration
public class SecurityConfig {

	@Configuration 
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter { 
	 
	  @Override 
	  protected void configure(HttpSecurity http) throws Exception { 
	    
	   /*String authorizedPaths[] = {"/", "/fundas/**"}; 
	         http 
	          .authorizeRequests() 
	              .antMatchers(authorizedPaths).permitAll()  
	              .anyRequest().authenticated() 
	              .and() 
	          .formLogin() 
	              .loginPage("/login") 
	              .permitAll() 
	              .and() 
	          .logout()                                     
	              .permitAll() 
	              .and() 
	          .csrf() 
	              .disable(); */
	 
			http.formLogin().and().logout().and().authorizeRequests() 
		    .antMatchers("/fundas/rest/").permitAll().anyRequest() 
		    .authenticated().and().csrf() 
		    .csrfTokenRepository(csrfTokenRepository()).and() 
		    .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class); 
			 
	  } 
	   
	  private Filter csrfHeaderFilter() { 
	   return new OncePerRequestFilter() { 
	    @Override 
	    protected void doFilterInternal(HttpServletRequest request, 
	      HttpServletResponse response, FilterChain filterChain) 
	      throws ServletException, IOException { 
	     CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName()); 
	     if (csrf != null) { 
	      Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN"); 
	      String token = csrf.getToken(); 
	      if (cookie == null || token != null 
	        && !token.equals(cookie.getValue())) { 
	       cookie = new Cookie("XSRF-TOKEN", token); 
	       cookie.setPath("/"); 
	       response.addCookie(cookie); 
	      } 
	     } 
	     filterChain.doFilter(request, response); 
	    }

		
	   }; 
	  } 
	 
	  private CsrfTokenRepository csrfTokenRepository() { 
	   HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository(); 
	   repository.setHeaderName("X-XSRF-TOKEN"); 
	   return repository; 
	  } 
	 } 
}
