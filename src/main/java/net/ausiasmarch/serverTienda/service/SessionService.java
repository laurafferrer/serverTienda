package net.ausiasmarch.serverTienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import net.ausiasmarch.serverTienda.bean.UserBean;
import net.ausiasmarch.serverTienda.entity.UserEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.helper.JWTHelper;
import net.ausiasmarch.serverTienda.repository.UserRepository;

@Service
public class SessionService {
    
    @Autowired
    UserRepository oUserRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public String login(UserBean oUserBean) {
        oUserRepository.findByUsernameAndPassword(oUserBean.getUsername(), oUserBean.getPassword()).orElseThrow(() -> new ResourceNotFoundException("Wrong User or password"));
        return JWTHelper.generateJWT(oUserBean.getUsername());
    }

    public String getSessionUsername() {
        if (oHttpServletRequest.getAttribute("username") instanceof String) {
            return oHttpServletRequest.getAttribute("username").toString();
        } else {
            return null;
        }
    }

    public UserEntity getSessionUser() {
        if (this.getSessionUser() != null ) {
            return oUserRepository.findByUsername(this.getSessionUsername()).orElse(null);
        } else {
            return null;
        }
    }

    public Boolean isSessionActive() {
        if (this.getSessionUsername() != null) {
            return oUserRepository.findByUsername(this.getSessionUsername()).isPresent();
        } else {
            return false;
        }
    }

    // FALTA SI FINALMENTE SE USA CUENTA DE ADMIN Y CUENTA DE USER.


}
