package com.akhona.authentication.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public OpenAPI authServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Authentication API")
                        .description("Authentication API")
                        .version("1.0"));
    }
}
