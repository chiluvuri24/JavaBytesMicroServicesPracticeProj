package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.AccountsDTO;
import com.eazybytes.accounts.dto.CardsDTO;
import com.eazybytes.accounts.dto.CustomerDetailsDTO;
import com.eazybytes.accounts.dto.LoansDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomerDetailsService;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerDetailsService implements ICustomerDetailsService {

    private CustomerRepository customerRepository;

    private AccountsRepository accountsRepository;

    private CardsFeignClient cardsFeignClient;

    private LoansFeignClient loansFeignClient;


    @Override
    public CustomerDetailsDTO fetchCustomerDetailsDTO(String mobileNumber,String corelationId) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> (
                new ResourceNotFoundException("Customer", "MObileNumber", mobileNumber)
        ));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() ->
                (new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()))
        );


        AccountsDTO accountsDTO = AccountsMapper.mapToAccountsDto(accounts, new AccountsDTO());
        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDTO());
        customerDetailsDTO.setAccountsDTO(accountsDTO);

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails( corelationId, mobileNumber);

        customerDetailsDTO.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDTO> cardsDTOResponseEntity = cardsFeignClient.fetchCardByMobileNumber(corelationId, mobileNumber);

        customerDetailsDTO.setCardsDTO(cardsDTOResponseEntity.getBody());

        return customerDetailsDTO;
    }
}
