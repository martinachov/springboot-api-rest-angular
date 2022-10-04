package ar.gob.mecon.dgsiaf.presupuestoconsultas.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

  @Bean
  public Docket apiDocket(Environment environment) {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .apiInfo(getApiInfo())
        .enable(true);
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo(
        "PresupuestoConsultasAppStarter",
        "Aplicación starter para Axon",
        "0.1.0",
        "Terms of Service",
        new Contact("Jhon Doe", "DGSIAF", "jdoe@mecon.gov.ar"),
        "",
        "",
        Collections.emptyList());
  }
}
