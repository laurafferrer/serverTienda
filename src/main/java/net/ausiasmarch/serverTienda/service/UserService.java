package net.ausiasmarch.serverTienda.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ausiasmarch.serverTienda.entity.UserEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.helper.DataGenerationHelper;
import net.ausiasmarch.serverTienda.repository.UserRepository;

@Service
public class UserService {

    private final String tiendaPASSWORD = "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e";

    @Autowired
    UserRepository oUserRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    @Autowired
    EmailService oEmailService;
    
    public UserEntity get(Long id) {
        return oUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    public UserEntity getByUsername(String username) {
        return oUserRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found by username"));
    }


    public Page<UserEntity> getPage(Pageable oPageable, String filter) {
        oSessionService.onlyAdmins();

        Page<UserEntity> oPage;

        if (filter == null || filter.isEmpty() || filter.trim().isEmpty()) {
            oPage = oUserRepository.findAll(oPageable);
        } else {
            oPage = oUserRepository.findByUserByNameOrLastnameContainingIgnoreClase(filter, filter, filter, filter, null);
        }
        return oPage;
    }

    public Long create (UserEntity oUserEntity) {
        oSessionService.onlyAdmins();
        oUserEntity.setId(null);
        oUserEntity.setPassword(tiendaPASSWORD);
        //POSIBLE TOKEN -> oUserEntity.setToken(UUID.randomUUID().toString());
        oUserRepository.save(oUserEntity);
        //POSIBLE RECUPERAR CONTRASEÑA POR CORREO -> this.sendEmail(oUserEntity);
        return oUserEntity.getId();
    }

    public Long createForUsers(UserEntity oUserEntity) {
        oUserEntity.setId(null);
        oUserEntity.setPassword(tiendaPASSWORD);
        //POSIBLE TOKEN -> oUserEntity.setToken(UUID.randomUUID().toString()); // genero el token
        oUserEntity.setRole(true); // role = true -> user
        oUserRepository.save(oUserEntity);
        //POSIBLE RECUPERAR CONTRASEÑA POR CORREO -> this.sendEmail(oUserEntity); // envio el email
        return oUserEntity.getId();
    }

    /*
     * POSIBLE USO DE RECUPERACIÓN DE CONTRASEÑA POR CORREO
     * 
     * Send email to user with token
     * 
     * @param user
    public void sendEmail(UserEntity user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:4200/user/confirm-account?token=" + user.getToken());
        oEmailService.sendEmail(mailMessage);
    }

     * Confirm email
    public ResponseEntity<?> confirmCorreo(String tokenVerificacion, String password) {
        UserEntity oUser = oUserRepository.findByToken(tokenVerificacion)
                .orElseThrow(() -> new RuntimeException("Token not found when validatimg token"));
        oUser.setVerified(true);
        oUser.setToken(null);
        oUser.setPassword(password);
        oUserRepository.save(oUser);
        return ResponseEntity.ok("Email verified successfully!");
    }
     * 
     */


    public UserEntity update(UserEntity oUserEntityToSet) {
        UserEntity oUserEntityFromDatabase = this.get(oUserEntityToSet.getId());
        oSessionService.onlyAdminsOrUsersWithIisOwnData(oUserEntityFromDatabase.getId());
        if( oSessionService.isUser()) {
            oUserEntityToSet.setRole(oUserEntityFromDatabase.getRole());
            oUserEntityToSet.setPassword(tiendaPASSWORD);
            return oUserRepository.save(oUserEntityToSet);
        } else {
            oUserEntityToSet.setPassword(tiendaPASSWORD);
            return oUserRepository.save(oUserEntityToSet);
        }
    }

    public Long delete(Long id) {
       oSessionService.onlyAdmins();
       oUserRepository.deleteById(id);
       return id;
    }

    public UserEntity getOneRandom() {
        oSessionService.onlyAdmins();
        Pageable oPageable = PageRequest.of((int) (Math.random() * oUserRepository.count()), 1);
        return oUserRepository.findAll(oPageable).getContent().get(0);
    }

    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            String dni = DataGenerationHelper.getRandomDni();
            String name = DataGenerationHelper.getRandomName();
            String lastName1 = DataGenerationHelper.getRandomLastName();
            String lastName2 = DataGenerationHelper.getRandomLastName();
            String username = DataGenerationHelper.doNormalize(name.substring(0, 3) + lastName1.substring(1, 3) + lastName2.substring(1, 2) + i).toLowerCase();
            LocalDate birthDate = DataGenerationHelper.getRandomBirthDate();
            String numberPhone = DataGenerationHelper.getRandomNumberPhone();
            String email = (name.substring(0, 3) + lastName1.substring(0, 3) + lastName2.substring(0, 3) + i).toLowerCase() + "@gmail.com";
            String address = DataGenerationHelper.getRandomAddress();
            String city = DataGenerationHelper.getRandomCity();
            int postalCode = DataGenerationHelper.getRandomPostalCode();
            oUserRepository.save(new UserEntity(dni, username, "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", name, lastName1, lastName2, birthDate, numberPhone, email, address, city, postalCode, true));
        }
        return oUserRepository.count();
    }

    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oUserRepository.deleteAll();
        oUserRepository.resetAutoIncrement();
        UserEntity oUserEntity1 = new UserEntity(1L, "00000000A", "marmarzo", tiendaPASSWORD, "Mario", "Marzo", "Cruz", LocalDate.of(1990, 10, 23), "666666666", "marmarzo@gmail.com", "Calle Mayor 1A", "Madrid", 28001, false);
        oUserRepository.save(oUserEntity1);
        UserEntity oUserEntity2 = new UserEntity(2L, "00000000B", "pepperez", tiendaPASSWORD, "Pepe", "Perez", "Cara", LocalDate.of(2000, 06, 13), "777777777", "pepperez@gmail.com", "Calle Menor 1A", "Barcelona", 28051, true);
        oUserRepository.save(oUserEntity2);
        return oUserRepository.count();
    }

    
}
