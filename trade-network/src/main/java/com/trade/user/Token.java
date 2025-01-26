package com.trade.user;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

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
