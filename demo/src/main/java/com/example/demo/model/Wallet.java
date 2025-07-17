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
@Table(name = "wallet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Wallet {

    @Column(nullable = false)
    private int cusId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int walId;

    @Column(nullable = false)
    private double walAmount;

    @Column(nullable = false)
    private String walSource;
}
