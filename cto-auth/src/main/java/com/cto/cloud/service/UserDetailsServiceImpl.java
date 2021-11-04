package com.cto.cloud.service;

import com.cto.cloud.dao.FrontUserMapper;
import com.cto.cloud.entity.FrontUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * 构建用户信息
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private FrontUserMapper frontUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		FrontUser frontUser = frontUserMapper.queryFrontUserByName(username);
		if(frontUser == null){
			throw new OAuth2Exception("用户名不存在");
		}
		return User.withUsername(username)
					.password(passwordEncoder.encode(frontUser.getPassword()))
					.authorities("ROLE_ADMIN")
					.build();
	}

}
