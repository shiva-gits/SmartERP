package com.smartERP.Backend.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stcok")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "reorder_level", nullable = false)
    private Integer reorderLevel;
}
