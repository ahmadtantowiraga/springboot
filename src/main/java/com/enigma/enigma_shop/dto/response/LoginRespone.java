package com.enigma.enigma_shop.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRespone {
    private String username;
    private String token;
    private List<String> roles;
}
