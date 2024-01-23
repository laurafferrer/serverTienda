package net.ausiasmarch.serverTienda.api;

//import java.util.UUID;

//import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.serverTienda.entity.UserEntity;
import net.ausiasmarch.serverTienda.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserApi {
    
    @Autowired
    UserService oUserService;

    // Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserService.get(id));
    }

    // Get user by username
    @GetMapping("/byUsername/{username}")
    public ResponseEntity<UserEntity> get(@PathVariable("username") String username) {
        return ResponseEntity.ok(oUserService.getByUsername(username));
    }

    // Get a cantity of users using pagination
    @GetMapping("")
    public ResponseEntity<Page<UserEntity>> getPage(
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getPage(oPageable));
    }

    // Get users with more purchase details
    @GetMapping("/byPurchaseDetailDesc")
    public ResponseEntity<Page<UserEntity>> getUsersByPurchaseDetailDesc(Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getUsersByPurchaseDetailDesc(oPageable));
    }

    // Get users with less purchase details
    @GetMapping("/byPurchaseDetailAsc")
    public ResponseEntity<Page<UserEntity>> getUsersByPurchaseDetailAsc(Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getUsersByPurchaseDetailAsc(oPageable));
    }

    // Get a random user
    @GetMapping("/random")
    public ResponseEntity<UserEntity> getOneRandom() {
        return ResponseEntity.ok(oUserService.getOneRandom());
    }

    // Create new user
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody UserEntity oUserEntity) {
        return ResponseEntity.ok(oUserService.create(oUserEntity));
    }

    // Update existing user
    @PutMapping("")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity oUserEntity) {
        return ResponseEntity.ok(oUserService.update(oUserEntity));
    }

    // Delete existing user
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserService.delete(id));
    }

    // Remove all users
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oUserService.empty());
    }

    // Generate random users (bajo una cantidad dada)
    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oUserService.populate(amount));
    }

    /* ???
    @PostMapping("/forusers")
    public ResponseEntity<Long> createForUsers(@RequestBody UserEntity oUserEntity) {
        return ResponseEntity.ok(oUserService.createForUsers(oUserEntity));
    }
    */
    /* Confirm email!
    @RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken, @RequestParam("password") String password) {
        return ResponseEntity.ok(oUserService.confirmEmail(confirmationToken, password));
    }
    */

}
