package com.eazybank.message.functions;

import com.eazybank.message.dto.AccountsMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountsMsgDTO,AccountsMsgDTO> email(){
        System.out.println(" testttttttt ");
        return accountsMsgDTO -> {
            log.info(" Sending Email with details ::"+accountsMsgDTO);
            return accountsMsgDTO;
        };
    }

    @Bean
    public Function<AccountsMsgDTO,Long> sms(){
        return accountsMsgDTO -> {
            log.info(" Sending SMS with details ::"+accountsMsgDTO.toString());
            return accountsMsgDTO.accountNumber();
        };
    }

}
