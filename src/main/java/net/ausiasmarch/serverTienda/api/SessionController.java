/*
   Controller for managing user sessions.
   Provides endpoints for pre-login and login with captcha.
*/
package net.ausiasmarch.serverTienda.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.ausiasmarch.serverTienda.bean.CaptchaBean;
import net.ausiasmarch.serverTienda.bean.CaptchaResponseBean;
import net.ausiasmarch.serverTienda.bean.UserBean;
import net.ausiasmarch.serverTienda.service.SessionService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    SessionService oSessionService;

    /*
     * Endpoint for pre-login operation, returns a captcha for user validation.
     * 
     * @return ResponseEntity with CaptchaResponseBean.
     */
    @GetMapping("/prelogin")
    public ResponseEntity<CaptchaResponseBean> prelogin() {
        return ResponseEntity.ok(oSessionService.prelogin());
    }

    /*
     * Endpoint for user login with captcha.
     * 
     * @param oCaptchaBean CaptchaBean containing user input.
     * @return ResponseEntity with a string response, typically a token.
     */
    @PostMapping("/loginCaptcha")
    public ResponseEntity<String> loginCaptcha(@RequestBody CaptchaBean oCaptchaBean) {
        return ResponseEntity.ok(oSessionService.loginCaptcha(oCaptchaBean));
    }

    /*
     * Endpoint for user login with credentials.
     * 
     * @param oUserBean UserBean containing user credentials.
     * @return ResponseEntity with a string response, typically a token.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserBean oUserBean) {
        return ResponseEntity.ok(oSessionService.login(oUserBean));
    }
    
}
