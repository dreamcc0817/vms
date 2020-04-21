package com.bonc.upms.dto;

import com.bonc.upms.entity.SysUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Title: vms
 * @Package: com.bonc.upms.dto
 * @Description:
 * @Author: dreamcc
 * @Date: 2020/4/19 22:11
 * @Version: V1.0
 */
@AllArgsConstructor
public class SysUserDetailsDTO implements UserDetails {

	private SysUser sysUser;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList("");
	}

	@Override
	public String getPassword() {
		return sysUser.getPassword();
	}

	@Override
	public String getUsername() {
		return sysUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return sysUser.getLockFlag() == 0 ? Boolean.TRUE : Boolean.FALSE;
	}
}
