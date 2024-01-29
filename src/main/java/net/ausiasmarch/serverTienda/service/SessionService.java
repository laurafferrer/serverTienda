package net.ausiasmarch.serverTienda.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.transaction.Transactional;

import net.ausiasmarch.serverTienda.bean.CaptchaBean;
import net.ausiasmarch.serverTienda.bean.CaptchaResponseBean;
import net.ausiasmarch.serverTienda.bean.UserBean;
import net.ausiasmarch.serverTienda.entity.CaptchaEntity;
import net.ausiasmarch.serverTienda.entity.PendentEntity;
import net.ausiasmarch.serverTienda.entity.UserEntity;

import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.exception.UnauthorizedException;

import net.ausiasmarch.serverTienda.helper.UserGenerationHelper;
import net.ausiasmarch.serverTienda.helper.JWTHelper;

import net.ausiasmarch.serverTienda.repository.CaptchaRepository;
import net.ausiasmarch.serverTienda.repository.CategoryRepository;
import net.ausiasmarch.serverTienda.repository.PendentRepository;
import net.ausiasmarch.serverTienda.repository.UserRepository;

@Service
public class SessionService {
    
    @Autowired
    UserRepository oUserRepository;

    @Autowired
    CategoryRepository oCategoryRepository;

    @Autowired
    CaptchaService oCaptchaService;

    @Autowired
    CaptchaRepository oCaptchaRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    PendentRepository oPendentRepository;

    public String login(UserBean oUserBean) {
        String strUsername = oUserBean.getUsername();

        oUserRepository.findByUsernameAndPassword(strUsername,oUserBean.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Wrong User"));
        return JWTHelper.generateJWT(oUserBean.getUsername());
    }

    // Method to get the session's username
    public String getSessionUsername() {
        if (oHttpServletRequest.getAttribute("username") instanceof String) {
            return oHttpServletRequest.getAttribute("username").toString();
        } else {
            return null;
        }
    }

    // Methos to get the session user
    public UserEntity getSessionUser() {
        if (this.getSessionUser() != null ) {
            return oUserRepository.findByUsername(this.getSessionUsername()).orElse(null);
        } else {
            return null;
        }
    }

    // Methos to check if the session is active
    public Boolean isSessionActive() {
        if (this.getSessionUsername() != null) {
            return oUserRepository.findByUsername(this.getSessionUsername()).isPresent();
        } else {
            return false;
        }
    }

    // Methos for role checks and access control
/* 
    // Admins y Users.
    public Boolean isAdmin() {
        if (this.getSessionUsername() != null) {
            UserEntity oUserEntityInSession = oUserRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return Boolean.FALSE.equals(oUserEntityInSession.getRole());
        } else {
            return false;
        }
    }

    public Boolean isUser() {
        if (this.getSessionUsername() != null) {
            UserEntity oUserEntityInSession = oUserRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return Boolean.TRUE.equals(oUserEntityInSession.getRole());
        } else {
            return false;
        }
    }

    public void onlyAdmins() {
        if (!this.isAdmin()) {
            throw new UnauthorizedException("Only admins can do this");
        }
    }

    public void onlyUsers() {
        if (!this.isUser()) {
            throw new UnauthorizedException("Only users can do this");
        }
    }

    public void onlyAdminsOrUsers() {
        if (!this.isSessionActive()) {
            throw new UnauthorizedException("Only admins or users can do this");
        }
    }

    // Throw unauthorized exception if the current user is not the target user
    public void onlyUsersWithTheirData(Long userId) {
        if (!this.isUser()) {
            throw new UnauthorizedException("Only users can perform this action");
        }
        if (!this.getSessionUser().getId().equals(userId)) {
            throw new UnauthorizedException("You can only access your own data");
        }
    }

    // Throw unauthorized exception if the current user is neither an admin nor the target user
    public void onlyAdminsOrUsersWithTheirData(Long userId) {
        if (this.isSessionActive()) {
            if (!this.isAdmin()) {
                if (!this.isUser()) {
                    throw new UnauthorizedException("Only users can perform this action");
                } else {
                    if (!this.getSessionUser().getId().equals(userId)) {
                        throw new UnauthorizedException("You can only access your own data");
                    }
                }
            }
        } else {
            throw new UnauthorizedException("You must log in to perform this action");
        }
    }
*/

    @Transactional
    public CaptchaResponseBean prelogin() {
        // Create a captcha for pre-login
        CaptchaEntity oCaptchaEntity = oCaptchaService.createCaptcha();

        // Create a new pendent entity associated with the captcha
        PendentEntity pendentEntity = new PendentEntity();
        pendentEntity.setCaptcha(oCaptchaEntity);
        pendentEntity.setTimecode(LocalDateTime.now());
        PendentEntity newPendentEntity = oPendentRepository.save(pendentEntity);

        // Generate a token for the pendent entity
        newPendentEntity.setToken(UserGenerationHelper.getSHA256(String.valueOf(newPendentEntity.getId()) + String.valueOf(oCaptchaEntity.getId()) + String.valueOf(UserGenerationHelper.getRandomInt(0, 9999))));
        oPendentRepository.save(newPendentEntity);

        // Prepare response with token and captcha image
        CaptchaResponseBean captchaResponseBean = new CaptchaResponseBean();
        captchaResponseBean.setToken(newPendentEntity.getToken());
        captchaResponseBean.setCaptchaImage(oCaptchaEntity.getImage());

        return captchaResponseBean;
    }

    public String loginCaptcha(@RequestBody CaptchaBean oCaptchaBean) {
         if (oCaptchaBean.getUsername() != null && oCaptchaBean.getPassword() != null) {
            // Validate user credentials
            UserEntity oUserEntity = oUserRepository.findByUsernameAndPassword(oCaptchaBean.getUsername(), oCaptchaBean.getPassword()).orElseThrow(() -> new ResourceNotFoundException("Incorrect user or password"));

            // Check if user entity is present
            if (oUserEntity!=null) {
                // Retrieve pendent entity using the provided token
                PendentEntity oPendentEntity = oPendentRepository.findByToken(oCaptchaBean.getToken()).orElseThrow(() -> new ResourceNotFoundException("Incorrect token"));

                LocalDateTime timecode = oPendentEntity.getTimecode();

                // Check if the captcha is still valid (within 120s)
                if (LocalDateTime.now().isAfter(timecode.plusSeconds(120))) {
                    throw new UnauthorizedException("Captcha expired");
                }

                // Validate the captcha answer
                if (oPendentEntity.getCaptcha().getText().equals(oCaptchaBean.getAnswer())) {
                    // Generate and return a JWT token upon successful validation
                    return JWTHelper.generateJWT(oCaptchaBean.getUsername());
                } else {
                    throw new UnauthorizedException("Incorrect captcha");
                }
            } else {
                throw new UnauthorizedException("Wrong User or password");
            }        
        } else {
            throw new UnauthorizedException("User not found");
        }
    }

}
