package com.xalts.multiUserApproval.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_approvals")
@Getter @Setter
public class UserApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uaid")
    private Long uaid;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private User approver;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_approved")
    private boolean isApproved;

    @Column(name = "approved_at")
    @CreationTimestamp
    private LocalDateTime approvedAt;
}
