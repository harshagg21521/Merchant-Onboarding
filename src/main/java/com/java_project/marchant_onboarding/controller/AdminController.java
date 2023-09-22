package com.java_project.marchant_onboarding.controller;

import com.java_project.marchant_onboarding.model.reponse.GetAllResponse;
import com.java_project.marchant_onboarding.model.reponse.AddMerchantResponse;
import com.java_project.marchant_onboarding.model.reponse.MessageResponse;
import com.java_project.marchant_onboarding.model.request.AddRequest;
import com.java_project.marchant_onboarding.model.request.LoginRequest;
import com.java_project.marchant_onboarding.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/login")
    public ResponseEntity<List<GetAllResponse>> login(@RequestBody @Valid LoginRequest loginRequest){
        return merchantService.login(loginRequest);
    }
    @PostMapping("/save")
    public ResponseEntity<AddMerchantResponse> addMerchant(@RequestBody @Valid AddRequest merchantRequest){
        return merchantService.addMerchant(merchantRequest);
    }

    @GetMapping("/get")
    public ResponseEntity<List<GetAllResponse>> getAllMerchants(@RequestParam(name = "business_name", required = false) String businessName){
        if (businessName != null && !businessName.isEmpty()) {

            return merchantService.getMerchantsByBusinessName(businessName);

        } else {
            return merchantService.getmerchants();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<MessageResponse> deleteMerchant (@PathVariable Long id){
        return merchantService.deleteMerchant(id);
    }

    @PutMapping("inactivation/{id}")
    public ResponseEntity<MessageResponse> inactivaton(@PathVariable Long id){
        return merchantService.inactivation(id);
    }
}
