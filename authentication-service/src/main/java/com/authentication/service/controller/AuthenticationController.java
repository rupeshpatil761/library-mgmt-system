package com.authentication.service.controller;

import com.authentication.service.model.JwtAuthenticationResponse;
import com.authentication.service.model.LoginUser;
import com.authentication.service.security.JwtTokenProvider;
import com.authentication.service.security.LoginUserService;
import com.authentication.service.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController()
public class AuthenticationController {

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    private JwtAuthenticationResponse login(@RequestBody @Valid LoginUser user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return new JwtAuthenticationResponse(jwt, tokenProvider.getUserIdFromJWT(jwt), userPrincipal.getUserRole());
    }

    @PostMapping("/register")
    private ResponseEntity<LoginUser> register(@RequestBody @Valid LoginUser user) {
        LoginUser newUser = loginUserService.updateUserTable(user);
        return ResponseEntity.ok(newUser);
    }

    static Mono<? extends Throwable> mapErrorStatusCode(ClientHttpResponse clientResponse, String resource) {
        switch (clientResponse.getStatusCode()) {
            case CONFLICT:
                return Mono.error(new RuntimeException(resource + " already exist"));
            case NOT_FOUND:
                return Mono.error(new RuntimeException(resource + " not found"));
            default:
                return Mono.error(new RuntimeException("Internal Server Error"));
        }
    }

}
