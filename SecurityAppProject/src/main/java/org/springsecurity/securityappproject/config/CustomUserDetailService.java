package org.springsecurity.securityappproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springsecurity.securityappproject.entity.Person;
import org.springsecurity.securityappproject.repository.EmpRepo;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private EmpRepo empRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = empRepo.findByEmail(email);

        if(person == null){
            throw new UsernameNotFoundException("User email not found");
        } else {
            return new CustomUser(person);
        }
    }
}
