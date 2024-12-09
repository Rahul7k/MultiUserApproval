package com.xalts.multiUserApproval.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long uid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(unique = true, name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

}
