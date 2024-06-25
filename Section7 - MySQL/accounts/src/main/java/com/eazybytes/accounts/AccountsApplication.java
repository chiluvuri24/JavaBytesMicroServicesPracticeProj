package com.eazybytes.accounts;

import com.eazybytes.accounts.dto.AccountContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info=@Info(
				title = "Accounts Microservice Rest API Documentation",
				description = "Eazybank Accounts microservice Rest API Doc",
				version = "v1",
				contact = @Contact(
						name="Rama Krishna Raju",
						email="tutor@rkr.com",
						url="https://www.rkr.com"
				),
				license = @License(
						name="Apache2.0",
						url="https://www.rkr.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Eazy Bank Accounts API Rest API Doc",
				url = "https://www.rkr.com"
		)
)
@EnableConfigurationProperties(value = {AccountContactInfoDTO.class})
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
