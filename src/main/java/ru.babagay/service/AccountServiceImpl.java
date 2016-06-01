package ru.babagay.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.babagay.entity.Account;
import ru.babagay.repository.AccountRepository;

import java.util.Set;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    @Qualifier("accountRespitory")
    private AccountRepository accountRepository;


    public boolean persist(String login, String pass, String name, String surname, String email ) {
        try {
            accountRepository.persist(new Account(login,pass, name, surname, email));
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean persist(String problem) {
        return false;
    }

    @Override
    public Set<String> getRandomData() {
        return accountRepository.getRandomData();
    }
}