package com.miranda.inventory_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer quantity;

    @Version
    private Integer version;

}
