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
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resId")  // maps to DB column "resId"
    private int resId;

    @Column(name = "resName", nullable = false)
    private String resName;

    @Column(name = "resLocation", nullable = false)
    private String resLocation;

    @Column(name = "resUsername", nullable = false, unique = true)
    private String resUsername;

    @ToString.Exclude
    @Column(name = "resPassword", nullable = false)
    private String resPassword;

    @Column(name = "role", nullable = false)
    private String role = "restaurant";
}
