package com.java_project.marchant_onboarding.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRequest {
    @Email
    @NotEmpty(message = "Email Shouldn't Be  Empty")
    @NotNull(message = "Email Shouldn't Be  null")
    private String email;

    @NotEmpty(message = "Phone Number Shouldn't Be  Empty")
    @NotNull(message = "Phone Number  Shouldn't Be  null")
    @Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$",message = "Invalid Phone Number ")
    private String phone;

    @NotEmpty(message = "BusinessName Shouldn't Be  Empty")
    @NotNull(message = "BusinessName Shouldn't Be  null")
    private String businessName;

    @NotEmpty(message = "MerchantType Shouldn't Be  Empty")
    @NotNull(message = "MerchantType Shouldn't Be  null")
    private String merchantType;

}
