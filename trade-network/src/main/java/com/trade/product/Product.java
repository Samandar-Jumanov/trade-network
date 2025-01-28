package com.trade.product;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Product {


    @Id
    @GeneratedValue
    private int id;
    @NotNull(message = "Product name is required ")
    @Column(nullable = false , unique = true )
    private  String name;

    @NotNull(message = "Product price is required")
    private  int price;

    @NotNull(message = "Product type is required")
    private  String   type ; // Used OR New // should use enum

    @NotNull(message = "Additional fee must be entered")
    private  boolean additionalFee;

    @NotNull(message = "Available time is required")
    private  int availableTime;


// Connections


    // Connection with media ( one to many )


    // Connection with user ( Many to one )


//    @ManyToOne




    // From to



}
