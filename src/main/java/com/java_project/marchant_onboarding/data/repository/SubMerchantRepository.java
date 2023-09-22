package com.java_project.marchant_onboarding.data.repository;

import com.java_project.marchant_onboarding.data.entity.SubMerchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubMerchantRepository extends JpaRepository<SubMerchant,Long> {


    @Query(value = "select * from sub_merchant where email=:email or phone=:phone",nativeQuery = true)
    SubMerchant checkUser(String email,String phone);

    @Query(value = "select * from sub_merchant where buisness_name=:businessName and is_delete=false and status= 'active'",nativeQuery = true)
    List<SubMerchant> getSubMerchantBySearch(String businessName);

    @Query(value = "select * from sub_merchant where is_delete=false and status = 'active'",nativeQuery = true)
    List<SubMerchant> getAllSubMerchant();

    @Query(value = "select * from sub_merchant where id=:id and is_delete=false",nativeQuery = true)
    SubMerchant checkId(Long id);
}
