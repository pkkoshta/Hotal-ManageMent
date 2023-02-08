package com.prashant.login;

import lombok.*;

@Data
@NoArgsConstructor
@Getter@Setter
@AllArgsConstructor
public class LoginRequest {

    private String username;
    private String password;
}
