package com.xalts.multiUserApproval.dao.repo;

import com.xalts.multiUserApproval.dao.entity.UserApproval;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApprovalRepository extends CrudRepository<UserApproval, Long> {

    @Query("SELECT t FROM Task t WHERE t.tid = :loginId")
    long countByTaskId(Long taskId);
}
