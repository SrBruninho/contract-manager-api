package com.gora.contractmanagerapi.infra.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI contractManagerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CMA - Contract Manager API")
                        .description("API for Contract Management")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("CMA Team")
                                .email("cma@test.com.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
