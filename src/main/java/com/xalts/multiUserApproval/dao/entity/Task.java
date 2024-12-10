package com.xalts.multiUserApproval.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter @Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Long tid;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "approver_emails")
    private List<String> approverEmails;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
