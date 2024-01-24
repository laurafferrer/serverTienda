/*
   Controller for managing CAPTCHA entities.
   Provides an endpoint for creating a new CAPTCHA.
*/
package net.ausiasmarch.serverTienda.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.serverTienda.entity.CaptchaEntity;
import net.ausiasmarch.serverTienda.service.CaptchaService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/captcha")
public class CaptchaApi {

    // Autowired annotation is used for automatic dependency injection.
    @Autowired
    CaptchaService oCaptchaService;

    /*
     * Endpoint for creating a new CAPTCHA.
     * 
     * @return ResponseEntity containing the CAPTCHA.
     */
    @PostMapping("/create")
    public ResponseEntity<CaptchaEntity> create() {
        return ResponseEntity.ok(oCaptchaService.createCaptcha());
    }
    
}
