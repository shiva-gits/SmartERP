package com.smartERP.Backend.Repository;

import com.smartERP.Backend.Entities.voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface voucherRepository extends JpaRepository<voucher, Long> {

    List<voucher> findByLedgerId(Long ledgerId);

    boolean existsByVoucherNumber(String voucherNumber);

}
