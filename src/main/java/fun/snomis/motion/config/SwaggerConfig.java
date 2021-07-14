package fun.snomis.motion.config;

import fun.snomis.motion.pojo.SwaggerProperties;
import io.swagger.annotations.ApiOperation;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger3配置类
 *
 * @author fibreyu
 * @since 1.0.0
 */
@Configuration
@EnableOpenApi
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {

    private SwaggerProperties properties;

    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.properties = swaggerProperties;
    }

    @Bean
    public Docket createRestApi() {
        System.out.println("http://localhost:" + properties.getPort() + "/swagger-ui/index.html");
        System.out.println("http://localhost:" + properties.getPort() + "/v3/api-docs");

        return new Docket(DocumentationType.OAS_30) //文档类型
                .apiInfo(apiInfo()) //设置包含在json ResourceListing响应中的api元信息
                .select()  //启动用于api选择的构建器
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("fun.snomis.motion.controller"))
                .paths(PathSelectors.any()) //路径过滤器（扫描所有路径）
                .build()
                .enable(properties.getEnable());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getTitle()) //api标题
                .description(properties.getDescription()) //api描述
                .contact(new Contact("fibreyu", "","fibreyu@gmail.com")) //本API负责人的联系信息
                .version(properties.getVersion()) //版本号
                .build();
    }

    private List<ApiKey> securitySchemes() {
        // 设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization","Header");
        result.add(apiKey);
        return result;
    }

    private List<SecurityContext> securityContexts() {
        // 设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/hello/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();

    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }

}
