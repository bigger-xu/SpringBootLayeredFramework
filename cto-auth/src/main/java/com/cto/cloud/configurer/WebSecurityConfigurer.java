
package com.cto.cloud.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Zhang Wei
 * @date 2020/3/2 00:31
 * @version v1.0.1
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	/**
	 * 1:
	 * 请求授权:
	 * spring security 使用以下匹配器来匹配请求路劲：
	 *      antMatchers:使用ant风格的路劲匹配
	 *      regexMatchers:使用正则表达式匹配路劲
	 * anyRequest:匹配所有请求路劲
	 * 在匹配了请求路劲后，需要针对当前用户的信息对请求路劲进行安全处理。
	 * 2:定制登录行为。
	 *      formLogin()方法定制登录操作
	 *      loginPage()方法定制登录页面访问地址
	 *      defaultSuccessUrl()登录成功后转向的页面
	 *      permitAll()
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/static/**").permitAll()
				.antMatchers("/register").permitAll()
				.anyRequest().authenticated()//其余所有请求都需要认证后才可访问
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.permitAll();//允许用户任意访问
		http.csrf().disable();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
