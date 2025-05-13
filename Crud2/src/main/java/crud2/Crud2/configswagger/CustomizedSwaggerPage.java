package crud2.Crud2.configswagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CustomizedSwaggerPage {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🛍️ Store API By - Rahul ")
                        .version("v1.0")
                        .description("This API allows you to manage Categories,Brands and Products")
                        .termsOfService("https://example.com/terms")
                        .contact(new Contact()
                                .name("Rahul")
                                .email("rahulkatre10@gmail.com")
                                .url("https://www.linkedin.com/in/rahul-katre10/")));

    }
}

// http://localhost:8080/swagger-ui/index.html#/     - url for swagger
