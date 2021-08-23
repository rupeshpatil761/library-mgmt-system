package com.authentication.service.security;

import com.authentication.service.model.LoginUser;
import com.authentication.service.repository.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginUserService implements UserDetailsService {
    @Autowired
    LoginUserRepository loginUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) //will have in future email/id
            throws UsernameNotFoundException {
        // Let people login with either username or email FUTURE
        Optional<LoginUser> user = loginUserRepository.findById(usernameOrEmail);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }
        else if (!user.get().getIsActive()){
            throw new RuntimeException("username : "+ usernameOrEmail + " is deactivated");
        }
        else {
            return UserPrincipal.create(user.get());
        }
    }
    public LoginUser updateUserTable(LoginUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return loginUserRepository.save(user);
    }
}
