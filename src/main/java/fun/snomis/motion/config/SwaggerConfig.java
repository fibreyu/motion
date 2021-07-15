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

import java.util.Collections;

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
                .securityContexts(Collections.singletonList(SecurityContext.builder()
                        .securityReferences(Collections.singletonList(SecurityReference.builder()
                                .scopes(new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")})
                                .reference("Authorization")
                                .build()))
                        // 声明作用域
                        .operationSelector(o->o.requestMappingPattern().matches("/.*"))
                        .build()))
                .securitySchemes(Collections.singletonList(HttpAuthenticationScheme.JWT_BEARER_BUILDER
                        // 显示用
                        .name("Authorization")
                        .build()))
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

}
