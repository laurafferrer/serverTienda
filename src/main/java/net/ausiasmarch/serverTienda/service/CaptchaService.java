package net.ausiasmarch.serverTienda.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import net.ausiasmarch.serverTienda.entity.CaptchaEntity;
import net.ausiasmarch.serverTienda.repository.CaptchaRepository;

@Service
public class CaptchaService {

    @Autowired
    private CaptchaRepository oCaptchaRepository;

    @Autowired
    private DefaultKaptcha oDefaultKaptcha;

    // Create new captcha entity
    public CaptchaEntity createCaptcha() {
        CaptchaEntity oCaptchaEntity = new CaptchaEntity();
        String text = oDefaultKaptcha.createText();
        byte[] image = generateCaptchaImage(text);
        oCaptchaEntity.setId(null);
        oCaptchaEntity.setText(text);
        oCaptchaEntity.setImage(image);
        return oCaptchaRepository.save(oCaptchaEntity);
    }

    // Get a random captcha from database
    public CaptchaEntity getRandomCaptcha() {
        List<CaptchaEntity> oCaptchaEntityList = oCaptchaRepository.findAll();
        if (oCaptchaEntityList.isEmpty()) {
            Random oRandom = new Random();
            int index = oRandom.nextInt(oCaptchaEntityList.size());
            return oCaptchaEntityList.get(index);
        } else {
            throw new RuntimeException("No captchas found in database");
        }
    }

    // Generate captcha image for the given text
    private byte[] generateCaptchaImage(String text) {
        BufferedImage bufferedImage = oDefaultKaptcha.createImage(text);
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception exception) {
            exception.printStackTrace();
            return new byte[0];
        }
    }

}