package com.smartERP.Backend.Repository;

import com.smartERP.Backend.Entities.ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ledgerRepository extends JpaRepository<ledger, Long> {
    Optional<ledger> findByAccountName(String accountName);

    boolean existsByAccountNumber(String accountName);

}
