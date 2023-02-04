package com.wellware.config;

import com.wellware.helpers.LocaleLangInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final LocaleLangInterceptor interceptor;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login-register").setViewName("templates/login-register.html");
    }

    /**
     * Set default local for our application
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    //    /**
//     * To handle switching to a new locale based on the value of the lang parameter
//     * appended to a request
//     *
//     * @return
//     */
    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");
        return localeInterceptor;
    }
    //
//    /**
//     * Add localeInterceptor to the application's interceptor registry.
//     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
        registry.addInterceptor(interceptor);

    }



    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
