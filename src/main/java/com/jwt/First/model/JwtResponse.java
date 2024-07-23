package com.jwt.First.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {
    private String jwtToken;
    private String username;
}
