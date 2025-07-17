package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vendor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ven_id")  // <-- Fix this!
    private int venId;

    @Column(name = "ven_name", nullable = false)
    private String venName;

    @Column(name = "ven_phn_no", nullable = false)
    private String venPhnNo;

    @Column(name = "ven_username", nullable = false, unique = true)
    private String venUsername;

    @ToString.Exclude
    @Column(name = "ven_password", nullable = false)
    private String venPassword;

    @Column(name = "ven_email", nullable = false, unique = true)
    private String venEmail;

    @Column(name = "role", nullable = false)
    private String role = "vendor";
}

