package com.java_project.marchant_onboarding.controller;


import com.java_project.marchant_onboarding.model.reponse.GetAllResponse;
import com.java_project.marchant_onboarding.model.reponse.MessageResponse;
import com.java_project.marchant_onboarding.model.request.AddRequest;
import com.java_project.marchant_onboarding.service.SubMerchantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {
    @Autowired
    private SubMerchantService subMerchantService;

    @PostMapping("/save")
    public   ResponseEntity<MessageResponse> addSubMerchant(@RequestBody @Valid AddRequest subMerchantRequest){
        return subMerchantService.addSubMerchant(subMerchantRequest);
    }

    @GetMapping("/get")
    public ResponseEntity<List<GetAllResponse>> getSubMerchants(@RequestParam(name = "business_name",required = false)String businessName){
        if (businessName != null && !businessName.isEmpty()) {
            return subMerchantService.getSubMerchantByBusinessName(businessName);
        } else {
            return subMerchantService.getAllSubMerchants();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSubMerchantById(@PathVariable Long id) {
        return subMerchantService.deleteSubMerchantById(id);
    }
    @PutMapping("/inactivation/{id}")
    public ResponseEntity<String> subMerchantInactivation(@PathVariable Long id){
        return subMerchantService.subMerchantInactivation(id);
    }





}
