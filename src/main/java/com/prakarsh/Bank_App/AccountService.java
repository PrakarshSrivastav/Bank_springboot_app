package com.prakarsh.Bank_App;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }
    public Optional<Account> getAccount(Long id){
        return accountRepository.findById(id);
    }
    public Account deposit(Long id, double amount){
        Account account=getAccount(id).orElseThrow(()->new RuntimeException("Account not found"));
        if(amount>0) {
            account.setBalance(account.getBalance() + amount);
        }else{
            throw new RuntimeException("Deposit needs to be positive");
        }
        return accountRepository.save(account);
    }
    public Account withdraw(Long id, double amount) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

//    public Account transfer(Long id1,Long id2, double amount){
//        Account account1 = getAccount(id1).orElseThrow(() -> new RuntimeException("Account not found"));
//        Account account2 = getAccount(id2).orElseThrow(() -> new RuntimeException("Account not found"));
//        if(account1.getBalance()>amount){
//            account1.setBalance(account1.getBalance() - amount);
//            account2.setBalance(account2.getBalance() + amount);
//            List<Account> accounts = Arrays.asList(account1, account2);
//            return accountRepository.saveAll(accounts).get(0);
//        }
//         // returns the first saved account (or both if needed)
//         else {
//            throw new RuntimeException("Insufficient balance in account1");
//        }
//    }
}
