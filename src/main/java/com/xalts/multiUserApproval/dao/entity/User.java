package com.xalts.multiUserApproval.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @Column(unique = true, name = "user_id")
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @PrePersist
    private void generateUserId() {
        if (this.userId == null) {
            this.userId = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        }
    }

}
