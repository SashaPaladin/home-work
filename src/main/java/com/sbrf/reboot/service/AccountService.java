package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountService {

    @NonNull
    private AccountRepository accountRepository;

    public boolean isClientHasContract(long clientId, long contractNumber) {
        return accountRepository.getAllAccountsByClientId(clientId).contains(contractNumber);
    }

    public boolean isContractHasCard(long contractNumber, long card) {
        return accountRepository.getAllCardsByContractNumber(contractNumber).contains(card);
    }

}
