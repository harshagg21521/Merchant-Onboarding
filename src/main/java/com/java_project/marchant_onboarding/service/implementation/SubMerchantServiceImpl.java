package com.java_project.marchant_onboarding.service.implementation;

import com.java_project.marchant_onboarding.ExceptionHandlers.ResourceNotFoundException;
import com.java_project.marchant_onboarding.data.entity.SubMerchant;
import com.java_project.marchant_onboarding.data.repository.SubMerchantRepository;
import com.java_project.marchant_onboarding.model.reponse.GetAllResponse;
import com.java_project.marchant_onboarding.model.reponse.MessageResponse;
import com.java_project.marchant_onboarding.model.request.AddRequest;
import com.java_project.marchant_onboarding.service.SubMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SubMerchantServiceImpl implements SubMerchantService {

    @Autowired
    SubMerchantRepository subMerchantRepository;

    @Override
    public ResponseEntity<MessageResponse> addSubMerchant(AddRequest subMerchantRequest) {
       try{
           if(subMerchantRepository.checkUser(subMerchantRequest.getEmail(),subMerchantRequest.getPhone())==null){
               MessageResponse subMerchantResponse=new MessageResponse();
               SubMerchant subMerchant=new SubMerchant();
               subMerchant.setEmail(subMerchantRequest.getEmail());
               subMerchant.setMerchantType(subMerchantRequest.getMerchantType());
               subMerchant.setPhone(subMerchantRequest.getPhone());
               subMerchant.setBuisnessName(subMerchantRequest.getBusinessName());
               subMerchant.setCreatedAt(new Date());
               subMerchant.setModifiedAt(new Date());
               subMerchant.setStatus("active");
               subMerchant.setIsDelete(false);

               subMerchantRepository.save(subMerchant);
               subMerchantResponse.setMessage("created successfully");
               return new ResponseEntity<>(subMerchantResponse, HttpStatus.OK);
           }
           else{
               throw new ResourceNotFoundException("Entry Already Exist");
           }

       }
       catch (Exception e){
           throw  e;
       }
    }

    @Override
    public ResponseEntity<List<GetAllResponse>> getSubMerchantByBusinessName(String businessName) {
        try {
            List<GetAllResponse> allSubMerchantResponsesList=new ArrayList<>();
            List<SubMerchant> subMerchantList=subMerchantRepository.getSubMerchantBySearch(businessName);
            if(!subMerchantList.isEmpty()){
                for(SubMerchant  subMerchant:subMerchantList){
                    GetAllResponse allSubMerchantResponse=new GetAllResponse();
                    allSubMerchantResponse.setId(subMerchant.getId());
                    allSubMerchantResponse.setEmail(subMerchant.getEmail());
                    allSubMerchantResponse.setPhone(subMerchant.getPhone());
                    allSubMerchantResponse.setMerchantType(subMerchant.getMerchantType());
                    allSubMerchantResponse.setBusinessName(subMerchant.getBuisnessName());
                    allSubMerchantResponse.setStatus(subMerchant.getStatus());
                    allSubMerchantResponsesList.add(allSubMerchantResponse);

                }
                return  new ResponseEntity<>(allSubMerchantResponsesList,HttpStatus.OK);

            }else{
                throw  new ResourceNotFoundException("User Not Found with Buisness Name="+businessName);
            }
        }
        catch(Exception e){
            throw  e;
        }
    }

    @Override
    public ResponseEntity<List<GetAllResponse>> getAllSubMerchants() {
      try
      {
          List<GetAllResponse> allSubMerchantResponsesList=new ArrayList<>();
          List<SubMerchant> subMerchantList=subMerchantRepository.getAllSubMerchant();
          for(SubMerchant  subMerchant:subMerchantList){
              GetAllResponse allSubMerchantResponse=new GetAllResponse();
              allSubMerchantResponse.setId(subMerchant.getId());
              allSubMerchantResponse.setEmail(subMerchant.getEmail());
              allSubMerchantResponse.setPhone(subMerchant.getPhone());
              allSubMerchantResponse.setMerchantType(subMerchant.getMerchantType());
              allSubMerchantResponse.setBusinessName(subMerchant.getBuisnessName());
              allSubMerchantResponse.setStatus(subMerchant.getStatus());
              allSubMerchantResponsesList.add(allSubMerchantResponse);
          }

          return new ResponseEntity<>(allSubMerchantResponsesList,HttpStatus.OK);
      }
      catch (Exception e)
      {
          throw  e;
      }
    }

    @Override
    public ResponseEntity<String> deleteSubMerchantById(Long id) {
      try
      {
          SubMerchant subMerchant=subMerchantRepository.checkId(id);
          if(subMerchant!=null){
              subMerchant.setIsDelete(true);
              subMerchantRepository.save(subMerchant);
              return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
          }
          else{
              throw  new ResourceNotFoundException("User Not Found with id:"+id);
          }
      }
      catch (Exception e){
          throw e;
      }
    }

    @Override
    public ResponseEntity<String> subMerchantInactivation(Long id) {
        try{
            SubMerchant subMerchant=subMerchantRepository.checkId(id);
            if (subMerchant!=null){
                if(subMerchant.getStatus().equals("active")){
                    subMerchant.setStatus("Inactive");
                }
                else{
                    subMerchant.setStatus("active");
                }
                subMerchantRepository.save(subMerchant);
                return new ResponseEntity<>("Changed Successfully",HttpStatus.OK);
            }
            else{
                throw  new ResourceNotFoundException("User Not Found with id:"+id);
            }
        }
        catch (Exception e)
        {
            throw  e;
        }
    }
}
