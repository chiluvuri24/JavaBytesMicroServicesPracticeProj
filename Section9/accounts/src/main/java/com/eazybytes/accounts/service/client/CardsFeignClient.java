package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.CardsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping("/api/cards/fetch")
    public ResponseEntity<CardsDTO> fetchCardByMobileNumber(@RequestHeader("eazybank-corelation-id") String corelationId,
                                                            @RequestParam String mobileNumber);

}
