package com.java_project.marchant_onboarding.service;

import com.java_project.marchant_onboarding.model.reponse.GetAllResponse;
import com.java_project.marchant_onboarding.model.reponse.MessageResponse;
import com.java_project.marchant_onboarding.model.request.AddRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubMerchantService {
    public ResponseEntity<MessageResponse> addSubMerchant(AddRequest subMerchantRequest);

    public ResponseEntity<List<GetAllResponse>> getSubMerchantByBusinessName(String  businessName);

    public ResponseEntity<List<GetAllResponse>>  getAllSubMerchants();

    public ResponseEntity<String> deleteSubMerchantById(Long id);
    public ResponseEntity<String> subMerchantInactivation(Long id);
}
