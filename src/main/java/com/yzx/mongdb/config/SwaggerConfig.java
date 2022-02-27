package com.yzx.mongdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        // 添加全局变量 （可以不设置）
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder token = new ParameterBuilder();
        token.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(token.build());
        // 构建Docket对象
        return new Docket(DocumentationType.SWAGGER_2)
                // 设置全局变量
                .globalOperationParameters(pars)
                // 配置响应出错时对应的描述
                .globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                .globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                // 设置swaager 上显示的基本 Api 信息
                .apiInfo(apiInfo())
                .select()
                // 配置自己项目中引用swaager注解的路径
                .apis(RequestHandlerSelectors.basePackage("com.yzx"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 设置swagger 上的基本信息
     *
     * @return ApiInfo 对象
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            // 设置标题
            .title("swagger-api文档")
            // 设置描述
            .description("swagger接入教程")
             // 设置服务条款网址
            .termsOfServiceUrl("https://localhost:8080")
            .version("1.0")
            // 联系我们
            .contact(new Contact("联系我们", "http://www.baidu.com", "1205793288@qq.com"))
            .build();
    }

    /**
     * 设置请求响应返回的状态码对应的值
     *
     * @return list
     */
    private List<ResponseMessage> customerResponseMessage() {
        List<ResponseMessage> list = new ArrayList<>();
        list.add(new ResponseMessageBuilder().code(200).message("请求成功").build());
        list.add(new ResponseMessageBuilder().code(201).message("资源创建成功").build());
        list.add(new ResponseMessageBuilder().code(204).message("服务器成功处理了请求，但不需要返回任何实体内容").build());
        list.add(new ResponseMessageBuilder().code(400).message("请求失败,具体查看返回业务状态码与对应消息").build());
        list.add(new ResponseMessageBuilder().code(401).message("请求失败,未经过身份认证").build());
        list.add(new ResponseMessageBuilder().code(405).message("请求方法不支持").build());
        list.add(new ResponseMessageBuilder().code(415).message("请求媒体类型不支持").build());
        list.add(new ResponseMessageBuilder().code(500).message("服务器遇到了一个未曾预料的状况,导致了它无法完成对请求的处理").build());
        list.add(new ResponseMessageBuilder().code(503).message("服务器当前无法处理请求,这个状况是临时的，并且将在一段时间以后恢复").build());
        return list;
    }
}
