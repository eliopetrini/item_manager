package com.itemmanager.itemmanager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoredItemsRepository extends CrudRepository<StoredItems, Long> {}
