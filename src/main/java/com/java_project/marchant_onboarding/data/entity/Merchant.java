package com.java_project.marchant_onboarding.data.entity;

import java.util.Date;
import jakarta.persistence.*;


@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="buisness_name")
    private String BusinessName;

    @Column(name="merchant_type")
    private String MerchantType;

    @Column(name="uniqueid")
    private String UniqueId;

    @Column(name="password")
    private String passowrd;

    @Column(name="is_delete")
    private Boolean IsDelete;

    @Column(name = "status")
    private String status;

    @Column(name = "role")
    private String role;

    @Column(name="created_at")
    private Date CreatedAt;

    @Column(name="modified_at")
    private Date ModifiedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessName() {

        return BusinessName;
    }

    public void setBusinessName(String buisnessName) {
        this.BusinessName = buisnessName;
    }

    public String getMerchantype() {
        return MerchantType;
    }

    public void setMerchantType(String merchantype) {
        MerchantType = merchantype;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public Boolean getDelete() {
        return IsDelete;
    }

    public void setDelete(Boolean delete) {
        IsDelete = delete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public Date getModifiedAt() {
        return ModifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        ModifiedAt = modifiedAt;
    }
}
