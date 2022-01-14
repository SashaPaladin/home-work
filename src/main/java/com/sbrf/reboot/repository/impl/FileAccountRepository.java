package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.repository.AccountRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


public class FileAccountRepository implements AccountRepository {

    private final String filePath;

    private static final Logger log = Logger.getLogger(FileAccountRepository.class.getName());

    public FileAccountRepository (String filePath){
        this.filePath = filePath;
    }

    @Override
    public Set<Long> getAllAccountsByClientId(long clientId) {
        Set<Long> setOfNumbers = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            long number;
            long clientIdParse = 0;
            while((line = reader.readLine()) != null) {
                if (line.contains("clientId")) {
                    clientIdParse = Long.parseLong(line.substring(line.lastIndexOf(" ") + 1, line.lastIndexOf(",")));
                }
                if (line.contains("number") && clientId == clientIdParse) {
                    number = Long.parseLong(line.substring(line.lastIndexOf(" ") + 1));
                    setOfNumbers.add(number);
                }
            }
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException("Файл не найден");
        }
        return setOfNumbers;
    }

    @Override
    public Set<Long> getAllCardsByContractNumber(long contractNumber) {
        return null;
    }
}
