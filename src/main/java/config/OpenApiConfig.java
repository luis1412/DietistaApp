package config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
http://localhost:8080/v3/api-docs
http://localhost:8080/swagger-ui/index.html
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                addList("Bearer Authentication")).components(new Components().addSecuritySchemes
               ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info()
                .title("Dietista API")
                .description("API REST de administracion de empresa de Dietistas")
                .version("V.0.0.1")
                .license(new License().name("Apache 2.0").url("https://www.example.com/")))
                .externalDocs(new ExternalDocumentation()
                .description("Wiki Docs")
                .url("https://www.example.com/"));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

}
