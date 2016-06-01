package ru.babagay.service;

import java.util.Set;

public interface AccountService {

    public boolean persist(String problem);

    public Set<String> getRandomData();
}