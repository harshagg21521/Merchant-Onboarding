package com.java_project.marchant_onboarding.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull(message = "USERID SHOULDN'T  BE NULL")
    @NotEmpty(message = "USERID SHOULDN'T BE EMPTY")
    private String email;
    private String password;
}
