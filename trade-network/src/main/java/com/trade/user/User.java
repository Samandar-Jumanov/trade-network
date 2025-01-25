//Schema of database
// It defines all features and properties for user

package com.trade.user;




import com.trade.role.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter  // add getter methods
@Setter  // add  setter methods
@Builder //  - Lombok implements Builder pattern for object creation
@NoArgsConstructor // add constructor with no args
@AllArgsConstructor // ad constructor with all args
@Entity // highlight as entity
@Table(name = "user") // provide table name
@EntityListeners(AuditingEntityListener.class)  // ?

public class User implements UserDetails  , Principal {


    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private String password;
    private Boolean accountLocked;
    private Boolean enabled;

    @Column(unique = true)
    private String email;

    @CreatedDate
    @Column(updatable = false ,  nullable = false )
    private LocalDate createdAt;
    @LastModifiedDate // automatically record it is values
    @Column(insertable = false )
    private LocalDate updatedAt;


    //Connection wih roles
    @ManyToMany( fetch = FetchType.EAGER)
    private List<Roles> userRoles;


    @Override
    public String getName() {
        return firstname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRoles
                .stream()
                .map( r ->  new SimpleGrantedAuthority(r.getTitle()))
                .collect(Collectors.toList());


        /*
         *   Stream returns -> [ SimpleGrantedAuthority() , SimpleGrantedAuthority() ]
         *   Map gets each title from the authority
         *   and collect return it as a list [ admin , user , modifier etc ]
         *
         */


    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { // is account expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    private String getFullName() {
        return firstname + " " + lastname;
    }

}
