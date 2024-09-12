package org.libraryaccountingproject.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;

    @Configuration
    public class OpenApiConfig {

        @Bean
        public GroupedOpenApi publicApi() {
            return GroupedOpenApi.builder()
                    .group("Public")
                    .pathsToMatch("/api/public/**")
                    .pathsToMatch("/api/auth/**")
                    .build();
        }

        @Bean
        public GroupedOpenApi privateApi() {
            return GroupedOpenApi.builder()
                    .group("Private")
                    .pathsToMatch("/api/users/**")
                    .build();
        }

        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("Library Accounting Project")
                            .version("v1")
                            .description("Library API description"))
                    .components(new Components()
                            .addSecuritySchemes("Bearer",
                                    new SecurityScheme()
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")
                            ))
                    .addSecurityItem(new SecurityRequirement().addList("Bearer"));
        }
    }

