package com.authentication.service.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private String userRole;

    public JwtAuthenticationResponse(String accessToken, String username, String userRole) {
        this.accessToken = accessToken;
        this.username = username;
        this.userRole = userRole;
    }
}
