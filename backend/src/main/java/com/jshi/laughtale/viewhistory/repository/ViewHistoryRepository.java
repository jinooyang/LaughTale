package com.jshi.laughtale.viewhistory.repository;

import com.jshi.laughtale.viewhistory.domain.ViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {

}
