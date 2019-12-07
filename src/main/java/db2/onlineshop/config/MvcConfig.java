package db2.onlineshop.config;

import db2.onlineshop.web.handler.PermissionInterceptor;
import db2.onlineshop.web.handler.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"db2.onlineshop.web.controller"})
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/WEB-INF/resources/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(securityInterceptor)
        //        .addPathPatterns("/**");
        registry.addInterceptor(permissionInterceptor)
                .addPathPatterns("/v1/review/**")
                .addPathPatterns("/v1/movie/**");
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver result = new SpringResourceTemplateResolver();
        result.setApplicationContext(applicationContext);
        result.setPrefix("/WEB-INF/views/");
        result.setSuffix(".html");
        return result;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine result = new SpringTemplateEngine();
        result.setTemplateResolver(templateResolver());
        return result;
    }

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Autowired
    private PermissionInterceptor permissionInterceptor;

}
