package ntu.edu.quangnm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntu.edu.quangnm.entity.Account;
import ntu.edu.quangnm.repositories.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService{

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Integer id) {
        return accountRepository.findById(id);
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }

    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}

