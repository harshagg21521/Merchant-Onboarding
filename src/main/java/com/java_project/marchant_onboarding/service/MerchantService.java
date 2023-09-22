package com.java_project.marchant_onboarding.service;

import com.java_project.marchant_onboarding.model.reponse.GetAllResponse;
import com.java_project.marchant_onboarding.model.reponse.MessageResponse;
import com.java_project.marchant_onboarding.model.reponse.AddMerchantResponse;
import com.java_project.marchant_onboarding.model.request.AddRequest;
import com.java_project.marchant_onboarding.model.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MerchantService {
    public ResponseEntity<AddMerchantResponse> addMerchant(AddRequest merchantRequest);
    public ResponseEntity<List<GetAllResponse>> login(LoginRequest adminRequest);

    public ResponseEntity<List<GetAllResponse>> getmerchants();
    public ResponseEntity<List<GetAllResponse>> getMerchantsByBusinessName(String buisnessName);

    public ResponseEntity<MessageResponse> deleteMerchant(Long id);

    public ResponseEntity<MessageResponse> inactivation(Long id);

}
