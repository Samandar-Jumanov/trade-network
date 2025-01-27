// roles schema
// Implements all fucntionality a role needs


package com.trade.role;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trade.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "roles")

public class Roles {

    @Id
    @GeneratedValue
    private Integer id;


    @Column(nullable = false , unique = true)
    private String title;


    // Connecting with user
    @ManyToMany(mappedBy = "roles") // defines what to type in Users entity
    @JsonIgnore
    private List<User> users;



    @CreatedDate
    @Column(nullable = false, updatable = false )
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;





}
