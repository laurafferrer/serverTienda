/*
   Configuration class for setting up CAPTCHA generation using Kaptcha library.
   It defines a bean method that returns a configured DefaultKaptcha object.
*/
package net.ausiasmarch.serverTienda.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import java.util.Properties;

@Configuration
public class CaptchaConfigurationBean {

    /*
     * Bean method to create and configure a DefaultKaptcha object.
     * 
     * @return DefaultKaptcha object configured with specified properties.
     */
    @Bean
    public DefaultKaptcha DefaultKaptcha() {
        // Set propierties for Captcha generation
        Properties oProperties = new Properties();
        oProperties.setProperty("kaptcha.border", "yes");
        oProperties.setProperty("kaptcha.border.color", "105,179,90");
        oProperties.setProperty("kaptcha.textproducer.font.color", "blue");
        oProperties.setProperty("kaptcha.image.width", "135");
        oProperties.setProperty("kaptcha.image.height", "40");
        oProperties.setProperty("kaptcha.textproducer.font.size", "30");
        oProperties.setProperty("kaptcha.session.key", "captchaCode");
        oProperties.setProperty("kaptcha.textproducer.char.length", "5");

        // Create a Config object with the specified properties.
        Config config = new Config(oProperties);

        // Create a DefaultKaptcha object and set its configuration.
        DefaultKaptcha oDefaultKaptcha = new DefaultKaptcha();
        oDefaultKaptcha.setConfig(config);

        // Return the configured DefaultKaptcha object.
        return oDefaultKaptcha;
    }
    
}
