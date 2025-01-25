package com.trade.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Token {

    @Id
    @GeneratedValue
    private Integer Id;


    private String token;

    private LocalDate createdAt;
    private LocalDate expiresAt;
    private LocalDate validatedAt;


    @ManyToOne
    @JoinColumn(name = "userId" , nullable = false)
    private User user;



}
