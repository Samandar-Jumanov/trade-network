package com.trade.role;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface  RoleRepo extends JpaRepository<Role , Integer> {

     Optional<Role> findByTitle (String title);


}
