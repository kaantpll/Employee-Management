package com.example.Managment.Managment.service;
import com.example.Managment.Managment.dto.UserRegistrationDto;
import com.example.Managment.Managment.model.Role;
import com.example.Managment.Managment.model.User;
import com.example.Managment.Managment.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User save(UserRegistrationDto userRegistrationDto){
       User user = new User(
               userRegistrationDto.getFirstName(),
               userRegistrationDto.getLastName(),
               userRegistrationDto.getEmail(),
               passwordEncoder.encode(userRegistrationDto.getPassword()),
               Arrays.asList(new Role("ROLE_USER")));

       return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
