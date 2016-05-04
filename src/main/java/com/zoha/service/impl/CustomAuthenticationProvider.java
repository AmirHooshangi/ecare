package com.zoha.service.impl;

import com.zoha.model.security.CerberusUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {


  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    CerberusUser cerberusUser = new CerberusUser();
    List<GrantedAuthority> grantedAuths = new ArrayList<>();
    return new UsernamePasswordAuthenticationToken(cerberusUser, authentication.getCredentials(), grantedAuths);
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass));
  }
}
