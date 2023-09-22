package com.java_project.marchant_onboarding.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "sub_merchant")
public class SubMerchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "buisness_name")
    private String buisnessName;

    @Column(name = "merchant_type")
    private String merchantType;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "status")
    private String status;

    @Column (name="created_at")
    private Date createdAt;

    @Column(name="modified_at")
    private Date modifiedAt;
}
