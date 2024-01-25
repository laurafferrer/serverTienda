package net.ausiasmarch.serverTienda.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafTemplateConfig {

    /**
     * Configures and creates a Thymeleaf template resolver using ClassLoader.
     * 
     * @return ClassLoaderTemplateResolver object configured for Thymeleaf templates.
     */
    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        // Create a new instance of ClassLoaderTemplateResolver
        ClassLoaderTemplateResolver oTemplateResolver = new ClassLoaderTemplateResolver();
        
        // Set the prefix for template files to be loaded from the "templates/" directory
        oTemplateResolver.setPrefix("templates/");
        
        // Set the suffix for template files to be ".html"
        oTemplateResolver.setSuffix(".html");
        
        // Set the template mode to "HTML5"
        oTemplateResolver.setTemplateMode("HTML5");
        
        // Set the character encoding to "UTF-8"
        oTemplateResolver.setCharacterEncoding("UTF-8");
        
        // Set the template mode explicitly to TemplateMode.HTML
        oTemplateResolver.setTemplateMode(TemplateMode.HTML);
        
        // Set the order of this resolver to 1
        oTemplateResolver.setOrder(1);
        
        // Enable checking for the existence of templates
        oTemplateResolver.setCheckExistence(true);

        // Return the configured ClassLoaderTemplateResolver
        return oTemplateResolver;
    }
}
