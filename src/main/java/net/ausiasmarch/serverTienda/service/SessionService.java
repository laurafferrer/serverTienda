package net.ausiasmarch.serverTienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import net.ausiasmarch.serverTienda.bean.UserBean;
import net.ausiasmarch.serverTienda.entity.UserEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.exception.UnauthorizedException;
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

    public void onlyUsersWithIisOwnData(Long id_user) {
        if (!this.isUser()) {
            throw new UnauthorizedException("Only users can do this");
        }
        if (!this.getSessionUser().getId().equals(id_user)) {
            throw new UnauthorizedException("Only users can do this");
        }
    }

    public void onlyAdminsOrUsersWithIisOwnData(Long id_user) {
        if (this.isSessionActive()) {
            if (!this.isAdmin()) {
                if (!this.isUser()) {
                    throw new UnauthorizedException("Only admins or users can do this");
                } else {
                    if (!this.getSessionUser().getId().equals(id_user)) {
                        throw new UnauthorizedException("Only admins or users with its own data can do this");
                    }
                }
            }
        } else {
            throw new UnauthorizedException("Only admins or users can do this");
        }
    }

    // Falta captcha.


}