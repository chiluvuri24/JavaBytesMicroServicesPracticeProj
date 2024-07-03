package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDTO;

public interface IAccountService {
    /*
     @Param customerDTO
     */
    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fechAccountDetailsByMobileNumber(String mobileNumber);

    boolean updateAccount(CustomerDTO customerDTO);

    boolean deleteAccount(String mobileNumber);

}
