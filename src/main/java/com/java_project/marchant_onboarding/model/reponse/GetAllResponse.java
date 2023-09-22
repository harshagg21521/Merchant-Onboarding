package com.java_project.marchant_onboarding.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class GetAllResponse {

    private Long id;

    private String email;

    private String phone;

    private String BusinessName;

    private String MerchantType;

    private String status;
}
