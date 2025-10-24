package com.example.ApisRest.servis;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       if(username.equals("sebas")){
           return User
                   .withUsername("sebas")
                   .password("{nopp}1234")
                   .roles("USER")
                   .build();
       }
       throw new UsernameNotFoundException("usuario no encontrado");
    }
}
