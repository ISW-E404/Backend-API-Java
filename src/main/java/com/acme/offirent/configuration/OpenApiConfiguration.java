package com.acme.offirent.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class OpenApiConfiguration {

    public OpenAPI offirentOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                .title("OffiRent Application API")
                .description(
                        "OffiRent API implemented with Spring Boot RESTfull service and documented using springdoc-openapi and OpenAPI 3.0"
                ));
    }
}

