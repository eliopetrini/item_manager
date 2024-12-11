package com.itemmanager.itemmanager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeLogRepository extends CrudRepository<ChangeLog, Long> {}
