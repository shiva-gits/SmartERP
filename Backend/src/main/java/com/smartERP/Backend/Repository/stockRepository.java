package com.smartERP.Backend.Repository;

import com.smartERP.Backend.Entities.stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface stockRepository extends JpaRepository<stock, Long> {
    List<stock> findByQuantityLessThanEqual(Integer reorderLevel);
}
