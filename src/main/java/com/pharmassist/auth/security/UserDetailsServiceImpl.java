package com.pharmassist.auth.security;

import com.pharmassist.auth.repository.AuthAdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthAdminRepository adminRepository;

    public UserDetailsServiceImpl(AuthAdminRepository adminRepository) {
        super();
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByEmail(username)
                .map(UserDetailsImpl::new)
                .orElseThrow(()-> new UsernameNotFoundException("failed to Authenticate admin"));
    }

}
