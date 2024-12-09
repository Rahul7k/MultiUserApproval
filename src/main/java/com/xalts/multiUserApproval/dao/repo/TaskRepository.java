package com.xalts.multiUserApproval.dao.repo;

import com.xalts.multiUserApproval.dao.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
