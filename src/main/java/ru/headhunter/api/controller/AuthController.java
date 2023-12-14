package ru.headhunter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.headhunter.api.dto.JwtTokenDTO;
import ru.headhunter.api.dto.UserDTO;
import ru.headhunter.api.model.ErrorResponse;
import ru.headhunter.api.service.UserService;
import ru.headhunter.api.util.JwtTokenUtil;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody UserDTO userDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(userDTO.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtTokenDTO(token));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> hadleException(BadCredentialsException e) {
        return new ResponseEntity<>(new ErrorResponse("Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
    }


}
