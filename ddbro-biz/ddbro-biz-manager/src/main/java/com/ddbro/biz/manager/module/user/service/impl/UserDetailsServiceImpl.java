//package com.ddbro.biz.ums.module.user.service.impl;
//
//
//import com.ddbro.biz.ums.module.user.mapper.UserMapper;
//import com.ddbro.biz.ums.module.user.pojo.UserDTO;
//import com.ddbro.security.config.LoginUser;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//	private final UserMapper userMapper;
//
//	public UserDetailsServiceImpl(UserMapper userMapper) {
//		this.userMapper = userMapper;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
//		UserDTO userDTO = this.userMapper.findByLoginName(loginName);
//		LoginUser customUser = new LoginUser(String.valueOf(userDTO.getId()),userDTO.getName(),null,null,null,null,null,null);
//		if (customUser == null) {
//			throw new UsernameNotFoundException("username " + loginName + " is not found");
//		}
////		return new CustomUserDetails(customUser);
//		return customUser;
//	}
//
////	static final class CustomUserDetails extends LoginUser implements UserDetails {
////
////		private static final List<GrantedAuthority> ROLE_USER = Collections
////				.unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));
////
////		CustomUserDetails(LoginUser loginUser) {
////			loginUser;
////
////        }
////
////		@Override
////		public Collection<? extends GrantedAuthority> getAuthorities() {
////			return ROLE_USER;
////		}
////
////		@Override
////		public String getUsername() {
////			return getEmail();
////		}
////
////		@Override
////		public boolean isAccountNonExpired() {
////			return true;
////		}
////
////		@Override
////		public boolean isAccountNonLocked() {
////			return true;
////		}
////
////		@Override
////		public boolean isCredentialsNonExpired() {
////			return true;
////		}
////
////		@Override
////		public boolean isEnabled() {
////			return true;
////		}
////
////	}
//}
