package com.java_project.marchant_onboarding.data.repository;

import com.java_project.marchant_onboarding.data.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant,Long> {

    @Query(value = "Select * from merchant where is_delete = false and is_delete = 'active'",nativeQuery = true)
    List<Merchant> getAllMerchants();

    @Query(value = "select * from merchant where email = :email",nativeQuery = true)
    Merchant checkId(String email);

    @Query(value = "select * from merchant where buisness_name=:buisness_name and is_delete=false and status = 'active'",nativeQuery = true)
    Merchant getMerchantBySearch(String buisness_name);

    @Query(value = "select * from merchant where id=:id and is_delete=false",nativeQuery = true)
    Merchant getMerchantById(Long id);

    @Query(value = "select * from merchant where email=:email or phone=:phone",nativeQuery = true)
    Merchant merchantExist(String email,String phone);



}
