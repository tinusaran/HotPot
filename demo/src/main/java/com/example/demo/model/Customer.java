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
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "cusPassword")  // 🔒 Avoid logging sensitive data
public class Customer {

 @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "CUS_ID") // <-- Add this!
private int cusId;


    @Column(nullable = false)
    private String cusName;

    @Column(nullable = false)
    private String cusPhnNo;

    @Column(nullable = false, unique = true)
    private String cusUsername;

    @Column(nullable = false)
    private String cusPassword;

    @Column(nullable = false, unique = true)
    private String cusEmail;

    @Column(name = "role", nullable = false)
    private String role = "customer";
}
