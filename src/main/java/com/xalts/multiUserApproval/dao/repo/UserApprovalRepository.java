package com.xalts.multiUserApproval.dao.repo;

import com.xalts.multiUserApproval.dao.entity.UserApproval;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApprovalRepository extends CrudRepository<UserApproval, Long> {

    @Query("SELECT COUNT(DISTINCT u.approverEmail) FROM UserApproval u WHERE u.task.tid = :taskId")
    int countByTaskId(Long taskId);
}
