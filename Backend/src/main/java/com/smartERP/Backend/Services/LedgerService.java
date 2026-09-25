package com.smartERP.Backend.Services;

import com.smartERP.Backend.Entities.Ledger;
import com.smartERP.Backend.DTOs.LedgerDTO;
import com.smartERP.Backend.Repository.LedgerRepository;
import com.smartERP.Backend.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    public LedgerService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    public List<LedgerDTO.LedgerResponse> getAllLedgers() {
        return ledgerRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public LedgerDTO.LedgerResponse createLedger(LedgerDTO.LedgerRequest ledgerRequest) {
        if (ledgerRepository.existsByAccountName(ledgerRequest.accountName())) {
            throw new IllegalArgumentException("Account not found under the name: " + ledgerRequest.accountName());
        }
        Ledger ledger = Ledger.builder()
                .accountName(ledgerRequest.accountName())
                .accountType(ledgerRequest.accountType())
                .balance(ledgerRequest.balance())
                .build();

        return mapToResponse(ledgerRepository.save(ledger));
    }

    public Ledger getLedgerEntity(Long id) {
        return ledgerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Ledger is present with the Id: " + id));
    }

    public LedgerDTO.LedgerResponse mapToResponse(Ledger ledger) {
        return new LedgerDTO.LedgerResponse(
                ledger.getId(),
                ledger.getAccountName(),
                ledger.getAccountType(),
                ledger.getBalance());

    }

}
