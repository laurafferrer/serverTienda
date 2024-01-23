package net.ausiasmarch.serverTienda.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import java.util.Properties;

@Configuration
public class CaptchaConfigurationBean {

    @Bean
    public DefaultKaptcha DefaultKaptcha() {
        Properties oProperties = new Properties();
        oProperties.setProperty("kaptcha.border", "yes");
        oProperties.setProperty("kaptcha.border.color", "105,179,90");
        oProperties.setProperty("kaptcha.textproducer.font.color", "blue");
        oProperties.setProperty("kaptcha.image.width", "135");
        oProperties.setProperty("kaptcha.image.height", "40");
        oProperties.setProperty("kaptcha.textproducer.font.size", "30");
        oProperties.setProperty("kaptcha.session.key", "captchaCode");
        oProperties.setProperty("kaptcha.textproducer.char.length", "5");
        Config config = new Config(oProperties);
        DefaultKaptcha oDefaultKaptcha = new DefaultKaptcha();
        oDefaultKaptcha.setConfig(config);
        return oDefaultKaptcha;
    }
    
}
