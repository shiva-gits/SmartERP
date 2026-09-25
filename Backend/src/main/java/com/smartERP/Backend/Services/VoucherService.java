package com.smartERP.Backend.Services;

import com.smartERP.Backend.Entities.Ledger;
import com.smartERP.Backend.Entities.Voucher;
import com.smartERP.Backend.DTOs.VoucherDTO;
import com.smartERP.Backend.Repository.VoucherRepository;
import com.smartERP.Backend.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VoucherService {

    private final VoucherRepository voucherRepository;
    private final LedgerService ledgerService;

    public VoucherService(VoucherRepository voucherRepository, LedgerService ledgerService) {
        this.voucherRepository = voucherRepository;
        this.ledgerService = ledgerService;
    }

    public List<VoucherDTO.VoucherResponse> getAllVouchers() {
        return voucherRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public VoucherDTO.VoucherResponse createVoucher(VoucherDTO.VoucherRequest voucherRequest) {
        if (voucherRepository.existsByVoucherNumber(voucherRequest.voucherNumber())) {
            throw new IllegalArgumentException("Voucher number already exists: " + voucherRequest.voucherNumber());
        }
        Ledger ledger = ledgerService.getLedgerEntity(voucherRequest.ledgerId());
        // update voucher balance based on voucher type
        if (voucherRequest.voucherType() == Voucher.VoucherType.RECEIPT) {
            ledger.setBalance(ledger.getBalance() + voucherRequest.amount());
        } else if (voucherRequest.voucherType() == Voucher.VoucherType.PAYMENT) {
            ledger.setBalance(ledger.getBalance() - voucherRequest.amount());
        }

        Voucher voucher = Voucher.builder()
                .voucherNumber(voucherRequest.voucherNumber())
                .voucherType(voucherRequest.voucherType())
                .amount(voucherRequest.amount())
                .transactionDate(voucherRequest.transactionDate())
                .description(voucherRequest.description())
                .ledger(ledger)
                .build();

        return mapToResponse(voucherRepository.save(voucher));

    }

    private VoucherDTO.VoucherResponse mapToResponse(Voucher voucher) {
        return new VoucherDTO.VoucherResponse(
                voucher.getId(),
                voucher.getVoucherNumber(),
                voucher.getVoucherType(),
                voucher.getAmount(),
                voucher.getTransactionDate(),
                voucher.getDescription(),
                voucher.getLedger().getId());
    }

}
