package ic.auth.service.controller;

import ic.auth.service.AuthService;
import ic.auth.service.exceptions.CustomException;
import ic.auth.service.model.User;
import ic.auth.service.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public String addNewUser(@RequestBody User user) {
        return authService.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return authService.generateToken(user.getName());
        } else {
            throw new CustomException("Invalid Request has been Initiated.", Constants.USER_INVALID, HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
