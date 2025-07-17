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
@Table(name = "ordersnew")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderNew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "custId", nullable = true)
    private int custId;

    @Column(name = "venId", nullable = true)
    private int venId;

    @Column(name = "MenuId", nullable = true)
    private int menuId;

    @Column(name = "WalSource", nullable = true)
    private String walSource;

    @Column(name = "quantityOrdered", nullable = false)
    private int quantityOrdered;

    @Column(name = "billAmount", nullable = true)
    private Double billAmount;

    @Column(name = "orderStatus", nullable = false)
    private String orderStatus;

    @Column(name = "comments", nullable = true)
    private String comments;
}
