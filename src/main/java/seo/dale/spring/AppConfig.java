package seo.dale.spring;

import org.springframework.context.annotation.*;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter({Controller.class, ControllerAdvice.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebMvcConfig.class)}
)
public class AppConfig {

        @Bean
        public FormattingConversionService conversionService() {
                // Use the DefaultFormattingConversionService but do not register defaults
                DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

                // Ensure @NumberFormat is still supported
                conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

                // Register date conversion with a specific global format
                DateFormatterRegistrar registrar = new DateFormatterRegistrar();
                registrar.setFormatter(new DateFormatter("yyyyMMdd"));
                registrar.registerFormatters(conversionService);

                return conversionService;
        }

}
