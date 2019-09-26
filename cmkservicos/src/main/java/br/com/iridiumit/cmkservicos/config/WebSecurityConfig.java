package br.com.iridiumit.cmkservicos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.iridiumit.cmkservicos.security.cmkUserDetailsService;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private cmkUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/").hasAnyRole("CMK_ADMIN", "CMK_COORDENADOR", "CMK_ANALISTA", "CMK_GESTOR")
				.antMatchers("/administracao/**").hasAnyRole("CMK_ADMIN")
				.antMatchers("/radministrativos/**").hasAnyRole("CMK_ADMIN","CMK_COORDENADOR","CMK_GESTOR")
				.antMatchers("/ratendimento/**").hasAnyRole("CMK_ADMIN","CMK_ANALISTA","CMK_COORDENADOR","CMK_GESTOR")
				.anyRequest()
				.authenticated()
				.antMatchers("/resources/**", "/signup", "/about").permitAll()
			.and()
			.formLogin()
				.loginPage("/entrar")
				.permitAll()
			.and()
			.logout()
				.logoutSuccessUrl("/entrar?logout")
				.permitAll()
			.and()
			.rememberMe()
				.userDetailsService(userDetailsService)
			.and()
			.exceptionHandling().accessDeniedPage("/acessonegado");;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/resources/**","/css/**","/images/**","/js/**","/webjars/**");
	}
}
