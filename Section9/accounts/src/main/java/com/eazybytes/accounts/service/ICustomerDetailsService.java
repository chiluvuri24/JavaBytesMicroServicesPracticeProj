package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDetailsDTO;

public interface ICustomerDetailsService {

    CustomerDetailsDTO fetchCustomerDetailsDTO(String mobileNumber, String coRelationId);

}
