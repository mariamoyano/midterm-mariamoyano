package com.ironhack.midtermmariamoyano.services.impl;

import com.ironhack.midtermmariamoyano.classes.Money;
import com.ironhack.midtermmariamoyano.controllers.dto.CheckingDTO;
import com.ironhack.midtermmariamoyano.controllers.dto.StudentCheckingDTO;
import com.ironhack.midtermmariamoyano.enums.Status;
import com.ironhack.midtermmariamoyano.models.AccountHolder;
import com.ironhack.midtermmariamoyano.models.Checking;
import com.ironhack.midtermmariamoyano.models.CreditCard;
import com.ironhack.midtermmariamoyano.models.StudentChecking;
import com.ironhack.midtermmariamoyano.repository.CheckingRepository;
import com.ironhack.midtermmariamoyano.repository.StudentCheckingRepository;
import com.ironhack.midtermmariamoyano.services.interfaces.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class CheckingServiceImpl implements CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Override
    public void updateCheckingBalance(Long id, int balance) {
        Optional <Checking> checkingOptional = checkingRepository.findById(id);
        checkingOptional.get().setBalance(new Money(BigDecimal.valueOf(balance)));
    }

    @Override
    public void transferMoney(String ownerName, long id) {

    }
    public void createCheckingAccount(CheckingDTO checkingDTO){
        Date date_of_birth = checkingDTO.getPrimaryOwner().getDate();
        Period years = Period.between(date_of_birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        int numberOfYears = years.getYears();

        if(numberOfYears<24){
            StudentChecking studentChecking = new StudentChecking(checkingDTO.getBalance(), checkingDTO.getSecretKey(),checkingDTO.getPrimaryOwner(),checkingDTO.getSecondaryOwner(),checkingDTO.getPenaltyFee(),checkingDTO.getCreationDate(),checkingDTO.getStatus());
            studentCheckingRepository.save(studentChecking);
        }else{
            Checking checking = new Checking(checkingDTO.getBalance(), checkingDTO.getSecretKey(),checkingDTO.getPrimaryOwner(),checkingDTO.getSecondaryOwner(),checkingDTO.getPenaltyFee(),checkingDTO.getMinimumBalance(),checkingDTO.getMonthlyMaintenanceFee(),checkingDTO.getCreationDate(),checkingDTO.getStatus());

            checkingRepository.save(checking);

        }

    }

    public Money getBalance(Long id) {
        Optional<Checking> checking = checkingRepository.findById(id);
        return checking.get().getBalance();
    }


}
