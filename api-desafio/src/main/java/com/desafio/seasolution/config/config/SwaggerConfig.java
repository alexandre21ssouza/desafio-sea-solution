package com.desafio.seasolution.config.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API de Empresa", version = "1.0", description = "API para gerenciamento de Setores, Cargos e Trabalhadores"))
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.desafio.seasolution.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
