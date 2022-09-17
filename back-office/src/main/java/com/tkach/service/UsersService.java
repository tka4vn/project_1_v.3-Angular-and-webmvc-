package com.tkach.service;

import com.tkach.model.Users;
import com.tkach.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return usersRepository.findByName(name);
    }

    @Transactional
    public void delete(Users user
    ) {
        usersRepository.delete(user);
    }
}
