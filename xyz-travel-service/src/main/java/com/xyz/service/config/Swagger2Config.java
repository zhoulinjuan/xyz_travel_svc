package com.xyz.service.config;

import com.xyz.common.constants.XyzConstant;
import com.xyz.common.vo.BaseErrorResult;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

  private static final ModelRef ERROR = new ModelRef(BaseErrorResult.class.getSimpleName());

  @Bean
  public Docket createRestApi() {

    List<ResponseMessage> globalResponses =
        Arrays.asList(
            new ResponseMessageBuilder()
                .code(500)
                .message("Server error")
                .responseModel(ERROR)
                .build(),
            new ResponseMessageBuilder()
                .code(400)
                .message("Bad request – wrong usage of the API")
                .responseModel(ERROR)
                .build(),
            new ResponseMessageBuilder()
                .code(401)
                .message("No or invalid authentication")
                .responseModel(ERROR)
                .build(),
            new ResponseMessageBuilder()
                .code(403)
                .message("Not permitted to access for users role")
                .responseModel(ERROR)
                .build(),
            new ResponseMessageBuilder()
                .code(404)
                .message("Requested resource not available (anymore)")
                .responseModel(ERROR)
                .build());

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.GET, globalResponses)
        .globalResponseMessage(RequestMethod.POST, globalResponses)
        .globalResponseMessage(RequestMethod.DELETE, globalResponses)
        .globalResponseMessage(RequestMethod.PATCH, globalResponses)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.xyz.service.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(XyzConstant.SWAGGER_TITLE_TRAVEL_SERVICE)
        .description("Provide basic data query and aggregation apis")
        .contact(new Contact("XYZ Travel Agency", "http://google.com/", "http://google.com/"))
        .version("0.0.1")
        .build();
  }
}
