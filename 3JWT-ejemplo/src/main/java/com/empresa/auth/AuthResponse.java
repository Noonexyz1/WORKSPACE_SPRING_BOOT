package com.empresa.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//esta clase hara la respuesta para devolver el TOKEN
public class AuthResponse {
    String token;
}
