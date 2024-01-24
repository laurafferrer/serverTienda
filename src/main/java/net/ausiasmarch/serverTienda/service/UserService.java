/* Service  that performs CRUD operations on the UserEntity entity*/
package net.ausiasmarch.serverTienda.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;

import java.time.LocalDate;

//import java.util.UUID;

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

    /*@Autowired
    EmailService oEmailService;*/
    
    // Get user by ID
    public UserEntity get(Long id) {
        return oUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    // Get user by username
    public UserEntity getByUsername(String username) {
        return oUserRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found by username"));
    }

    // Get a page of users
    public Page<UserEntity> getPage(Pageable oPageable) {
        return oUserRepository.findAll(oPageable);
    }

    // Create a new user
    public Long create (UserEntity oUserEntity) {
        //oSessionService.onlyAdmins();
        oUserEntity.setId(null);
        oUserEntity.setPassword(tiendaPASSWORD);
        //POSIBLE TOKEN -> oUserEntity.setToken(UUID.randomUUID().toString());
        oUserRepository.save(oUserEntity);
        //POSIBLE RECUPERAR CONTRASEÑA POR CORREO -> this.sendEmail(oUserEntity);
        return oUserEntity.getId();
    }

    // Update an existing user
    public UserEntity update(UserEntity oUserEntity) {
        //oSessionService.onlyAdmins();
        UserEntity oUserEntityFromDatabase = this.get(oUserEntity.getId());
        oUserEntity.setPassword(oUserEntityFromDatabase.getPassword());
        oUserEntity.setRole(oUserEntityFromDatabase.getRole());
        return oUserRepository.save(oUserEntity);
    }

    // Delete user by ID
    public Long delete(Long id) {
       //oSessionService.onlyAdmins();
       oUserRepository.deleteById(id);
       return id;
    }

    // Get one random user
    public UserEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oUserRepository.count()), 1);
        return oUserRepository.findAll(oPageable).getContent().get(0);
    }

    // Get users with purchase details ordered descending
    public Page<UserEntity> getUsersByPurchaseDetailDesc(Pageable oPageable) {
        return oUserRepository.findUsersByPurchaseDetailDesc(oPageable);
    }

    // Get users with purchase details ordered ascending
    public Page<UserEntity> getUsersByPurchaseDetailAsc(Pageable oPageable) {
        return oUserRepository.findUsersByPurchaseDetailAsc(oPageable);
    }

    // Populate the database with random users
    public Long populate(Integer amount) {
        //oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            String dni = DataGenerationHelper.getRandomDni();
            String name = DataGenerationHelper.getRandomName();
            String surname = DataGenerationHelper.getRandomLastName();
            String last_name = DataGenerationHelper.getRandomLastName();
            String username = DataGenerationHelper.doNormalize(name.substring(0, 3) + surname.substring(1, 3) + last_name.substring(1, 2) + i).toLowerCase();
            LocalDate birth_date = DataGenerationHelper.getRandombirth_date();
            String phone_number = DataGenerationHelper.getRandomphone_number();
            String email = (name.substring(0, 3) + surname.substring(0, 3) + last_name.substring(0, 3) + i).toLowerCase() + "@gmail.com";
            String address = DataGenerationHelper.getRandomAddress();
            String city = DataGenerationHelper.getRandomCity();
            int postal_code = DataGenerationHelper.getRandompostal_code();
            oUserRepository.save(new UserEntity(dni, username, "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", name, surname, last_name, birth_date, phone_number, email, address, city, postal_code, true));
        }
        return oUserRepository.count();
    }

    // Empty the user table and two sample users.
    @Transactional
    public Long empty() {
        //oSessionService.onlyAdmins();
        oUserRepository.deleteAll();
        oUserRepository.resetAutoIncrement();
        UserEntity oUserAdministrador = new UserEntity(1L, "00000000A", "marmarzo", tiendaPASSWORD, "Mario", "Marzo", "Cruz", LocalDate.of(1990, 10, 23), "666666666", "marmarzo@gmail.com", "Calle Mayor 1A", "Madrid", 52301, false);
        oUserRepository.save(oUserAdministrador);
        UserEntity oUser = new UserEntity(2L, "00000000B", "pepperez", tiendaPASSWORD, "Pepe", "Perez", "Cara", LocalDate.of(2000, 06, 13), "777777777", "pepperez@gmail.com", "Calle Menor 1A", "Barcelona", 52051, true);
        oUserRepository.save(oUser);
        return oUserRepository.count();
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
    
}
