package com.nelis.cnsd;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OpenAPIConfig {
    //    @Value("${openapi.dev-url}")
    private String devUrl = "localhost:8080";

    /*
        @Value("${openapi.prod-url}")
    */
    private String prodUrl = "blabla";

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Info info = new Info()
                .title("Demo Service API")
                .version("1.0")
                .description("This API exposes endpoints to manage demo.");

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
//    sup fucker
}