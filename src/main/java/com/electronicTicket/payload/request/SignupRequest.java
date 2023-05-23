package com.electronicTicket.payload.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
