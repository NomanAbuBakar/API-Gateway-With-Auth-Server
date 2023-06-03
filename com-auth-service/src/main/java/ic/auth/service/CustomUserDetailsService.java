package ic.auth.service;

import ic.auth.service.exceptions.CustomException;
import ic.auth.service.config.CustomUserDetails;
import ic.auth.service.model.User;
import ic.auth.service.repository.UserRepository;
import ic.auth.service.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws CustomException {
        Optional<User> user = userRepository.findByName(username);
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new CustomException("User not Found: "+username, Constants.USER_NOT_FOUND, HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN));
    }
}
