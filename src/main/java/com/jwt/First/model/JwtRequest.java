package com.jwt.First.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtRequest {
    private String email;
    private String password;
}
