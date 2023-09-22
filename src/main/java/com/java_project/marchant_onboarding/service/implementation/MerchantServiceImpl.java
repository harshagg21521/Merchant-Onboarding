package com.java_project.marchant_onboarding.service.implementation;

import com.java_project.marchant_onboarding.ExceptionHandlers.ResourceNotFoundException;
import com.java_project.marchant_onboarding.data.entity.Merchant;
import com.java_project.marchant_onboarding.data.entity.SubMerchant;
import com.java_project.marchant_onboarding.data.repository.MerchantRepository;
import com.java_project.marchant_onboarding.data.repository.SubMerchantRepository;
import com.java_project.marchant_onboarding.model.reponse.AddMerchantResponse;
import com.java_project.marchant_onboarding.model.reponse.GetAllResponse;
import com.java_project.marchant_onboarding.model.reponse.MessageResponse;
import com.java_project.marchant_onboarding.model.request.AddRequest;
import com.java_project.marchant_onboarding.model.request.LoginRequest;
import com.java_project.marchant_onboarding.service.MerchantService;
import com.java_project.marchant_onboarding.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    SubMerchantRepository subMerchantRepository;

    @Override
    public ResponseEntity<AddMerchantResponse> addMerchant(AddRequest merchantRequest) {
        try{
            Merchant merchant1=merchantRepository.merchantExist(merchantRequest.getEmail(),merchantRequest.getEmail());
            if(merchant1!=null){
                throw new ResourceNotFoundException("Entry Already Exist");
            }
            else{
                AddMerchantResponse merchantResponse = new AddMerchantResponse();
                String password=Utils.generatePassword();
                String ID=Utils.generateId();


                Merchant merchant = new Merchant();
                merchant.setEmail(merchantRequest.getEmail());
                merchant.setPhone(merchantRequest.getPhone());
                merchant.setBusinessName(merchantRequest.getBusinessName());
                if(merchantRequest.getMerchantType().equalsIgnoreCase("wholesaler")) {
                    merchant.setMerchantType(merchantRequest.getMerchantType());
                }
                else {
                    throw  new ResourceNotFoundException("Merchant type not exist");
                }
                merchant.setCreatedAt(new Date());
                merchant.setModifiedAt(new Date());
                merchant.setPassowrd(Utils.encrypt(password));
                merchant.setUniqueId(ID);
                merchant.setRole("merchant");
                merchant.setDelete(false);
                merchant.setStatus("active");
                merchantRepository.save(merchant);
                merchantResponse.setPassword(password);
                merchantResponse.setUserId(ID);
                return new ResponseEntity<>(merchantResponse, HttpStatus.OK);
            }
        }
        catch (Exception e){
            throw e;
        }
    }



    @Override
    public ResponseEntity<List<GetAllResponse>> getmerchants() {
     try
     {
         List<GetAllResponse> allmerchantList=new ArrayList<>();
         List<Merchant> merchants=merchantRepository.getAllMerchants();
         for(Merchant merchantData:merchants){
             GetAllResponse allMerchantsResponse=new GetAllResponse();
             allMerchantsResponse.setMerchantType(merchantData.getMerchantype());
             allMerchantsResponse.setId(merchantData.getId());
             allMerchantsResponse.setEmail(merchantData.getEmail());
             allMerchantsResponse.setPhone(merchantData.getPhone());
             allMerchantsResponse.setBusinessName(merchantData.getBusinessName());
             allMerchantsResponse.setStatus(merchantData.getStatus());
             allmerchantList.add(allMerchantsResponse);
         }
         return new ResponseEntity<>(allmerchantList,HttpStatus.OK);
     }
     catch(Exception e)
     {
         throw e;
     }
    }

    @Override
    public ResponseEntity<List<GetAllResponse>> getMerchantsByBusinessName(String buisnessName) {
       try
       {
           List<GetAllResponse> allmerchantList=new ArrayList<>();
           Merchant merchant=merchantRepository.getMerchantBySearch(buisnessName);
           if(merchant!=null){
               GetAllResponse allMerchantsResponse=new GetAllResponse();
               allMerchantsResponse.setMerchantType(merchant.getMerchantype());
               allMerchantsResponse.setId(merchant.getId());
               allMerchantsResponse.setEmail(merchant.getEmail());
               allMerchantsResponse.setPhone(merchant.getPhone());
               allMerchantsResponse.setBusinessName(merchant.getBusinessName());
               allMerchantsResponse.setStatus(merchant.getStatus());
               allmerchantList.add(allMerchantsResponse);
               return new ResponseEntity<>(allmerchantList,HttpStatus.OK);
           }
           else{
               throw  new ResourceNotFoundException("User Not Found with Buisness Name="+buisnessName);
           }
       }
       catch (Exception e)
       {
           throw e;
       }
    }

    @Override
    public ResponseEntity<MessageResponse> deleteMerchant(Long id) {
        try {
            Merchant merchant = merchantRepository.getMerchantById(id);
            MessageResponse messageResponse = new MessageResponse();
            if (merchant != null) {
                merchant.setDelete(true);

                List<SubMerchant> subMerchantList=subMerchantRepository.getSubMerchantBySearch(merchant.getBusinessName());
                if(!subMerchantList.isEmpty()){
                    for(SubMerchant subMerchant:subMerchantList){
                        subMerchant.setIsDelete(true);
                        subMerchantRepository.save(subMerchant);
                    }
                }

                merchantRepository.save(merchant);
                messageResponse.setMessage("Deleted Successfully");
                return new ResponseEntity<>(messageResponse, HttpStatus.OK);
            } else {
                throw new ResourceNotFoundException("User Not Found with id:" + id);
            }
        }
        catch(Exception e){
            throw e ;
        }
    }

    @Override
    public ResponseEntity<MessageResponse> inactivation(Long id) {
       try
       {
           Merchant merchant=merchantRepository.getMerchantById(id);
           MessageResponse messageResponse=new MessageResponse();
           if(merchant!=null){
               List<SubMerchant> subMerchantList=subMerchantRepository.getSubMerchantBySearch(merchant.getBusinessName());
               if(merchant.getStatus().equals("active")){
                   merchant.setStatus("Inactive");
                   if(!subMerchantList.isEmpty()){
                       for(SubMerchant subMerchant:subMerchantList){
                           subMerchant.setStatus("Inactive");
                           subMerchantRepository.save(subMerchant);
                       }
                   }
               }
               else{
                   merchant.setStatus("active");
                   if(!subMerchantList.isEmpty()){
                       for(SubMerchant subMerchant:subMerchantList){
                           subMerchant.setStatus("active");
                           subMerchantRepository.save(subMerchant);
                       }
                   }
               }


               merchantRepository.save(merchant);
               messageResponse.setMessage("Changed Successfully");
               return new ResponseEntity<>(messageResponse,HttpStatus.OK);
           }
           else{
               throw  new ResourceNotFoundException("User Not Found with id:"+id);
           }
       }
       catch(Exception e)
       {
           throw e;
       }
    }

    @Override
    public ResponseEntity<List<GetAllResponse>> login(LoginRequest loginRequest) {
       try{
           Merchant merchant = merchantRepository.checkId(loginRequest.getEmail());
           List<GetAllResponse> loginMerchantResponseList=new ArrayList<>();
           String str=Utils.encrypt(loginRequest.getPassword());
           if(merchant!=null){

               if(merchant.getRole().equalsIgnoreCase("admin")){

                   if(merchant.getPassowrd().equalsIgnoreCase(str)){
                       List<Merchant> merchants=merchantRepository.getAllMerchants();
                       for(Merchant merchantData:merchants){
                           GetAllResponse allGetResponse = new GetAllResponse();
                           allGetResponse.setMerchantType(merchantData.getMerchantype());
                           allGetResponse.setId(merchantData.getId());
                           allGetResponse.setEmail(merchantData.getEmail());
                           allGetResponse.setPhone(merchantData.getPhone());
                           allGetResponse.setBusinessName(merchantData.getBusinessName());
                           allGetResponse.setStatus(merchantData.getStatus());
                           loginMerchantResponseList.add(allGetResponse);
                       }
                   }
                   else{
                       throw new ResourceNotFoundException("Password don't match");
                   }
               }

               else if(merchant.getRole().equalsIgnoreCase("merchant")){

                   if(merchant.getPassowrd().equals(str)){
                       List<SubMerchant> submerchants=subMerchantRepository.getSubMerchantBySearch(merchant.getBusinessName());
                       for(SubMerchant subMerchant:submerchants){
                           GetAllResponse allGetResponse = new GetAllResponse();
                           allGetResponse.setMerchantType(subMerchant.getMerchantType());
                           allGetResponse.setId(subMerchant.getId());
                           allGetResponse.setEmail(subMerchant.getEmail());
                           allGetResponse.setPhone(subMerchant.getPhone());
                           allGetResponse.setBusinessName(subMerchant.getBuisnessName());
                           allGetResponse.setStatus(subMerchant.getStatus());
                           loginMerchantResponseList.add(allGetResponse);
                       }
                   }
                   else{
                       throw new ResourceNotFoundException("Password don't match");
                   }
               }
           }
           else{
               throw  new ResourceNotFoundException("Invalid email Id");
           }
           return new ResponseEntity<>(loginMerchantResponseList,HttpStatus.OK);
       }
       catch (Exception e){
           throw e;
       }

    }
}


