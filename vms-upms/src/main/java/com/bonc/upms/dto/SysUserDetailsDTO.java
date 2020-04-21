package com.bonc.upms.dto;

import com.bonc.upms.entity.SysResource;
import com.bonc.upms.entity.SysUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
	private List<SysResource> sysResourceList;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return sysResourceList.stream()
				.map(role->new SimpleGrantedAuthority(role.getResourceId()+":"+role.getName()))
				.collect(Collectors.toList());
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
