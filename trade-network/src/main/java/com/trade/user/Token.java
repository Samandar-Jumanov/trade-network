package com.trade.user;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private LocalDateTime createdAt;
    private LocalDateTime  expiresAt;
    private LocalDateTime  validatedAt;


    @ManyToOne
    @JoinColumn(name = "userId" , nullable = false)
    private User user;


}
