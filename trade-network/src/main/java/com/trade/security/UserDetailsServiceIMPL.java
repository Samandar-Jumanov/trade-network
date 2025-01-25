package com.trade.security;

import com.trade.user.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceIMPL implements UserDetailsService {


    private final  UserRepo repository;

    @Override
    @Transactional
    public UserDetails  loadUserByUsername (String email  )  throws UsernameNotFoundException  {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

}
