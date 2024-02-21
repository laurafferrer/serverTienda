/*
   RESTful API for managing User entities.
   Provides endpoints for CRUD operations and additional queries.
*/
package net.ausiasmarch.serverTienda.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PageableDefault;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import net.ausiasmarch.serverTienda.entity.UserEntity;
import net.ausiasmarch.serverTienda.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserApi {
    
    @Autowired
    UserService oUserService;

    /*
     * Get user by ID.
     * 
     * @param id User's ID.
     * @return ResponseEntity with UserEntity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserService.get(id));
    }

    /*
     * Get user by username.
     * 
     * @param username User's username.
     * @return ResponseEntity with UserEntity.
     */
    @GetMapping("/byUsername/{username}")
    public ResponseEntity<UserEntity> getByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(oUserService.getByUsername(username));
    }

    /*
     * Get a page of users using pagination.
     * 
     * @param oPageable Pageable object for pagination.
     * @return ResponseEntity with Page<UserEntity>.
     */
    @GetMapping("")
    public ResponseEntity<Page<UserEntity>> getPage(
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getPage(oPageable));
    }

    /*
     * Get users with more purchase details in descending order.
     * 
     * @param oPageable Pageable object for pagination.
     * @return ResponseEntity with Page<UserEntity>.
     */
    @GetMapping("/byPurchaseDetailDesc")
    public ResponseEntity<Page<UserEntity>> getUsersByPurchaseDetailDesc(Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getUsersByPurchaseDetailDesc(oPageable));
    }

    /*
     * Get users with less purchase details in ascending order.
     * 
     * @param oPageable Pageable object for pagination.
     * @return ResponseEntity with Page<UserEntity>.
     */
    @GetMapping("/byPurchaseDetailAsc")
    public ResponseEntity<Page<UserEntity>> getUsersByPurchaseDetailAsc(Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getUsersByPurchaseDetailAsc(oPageable));
    }

    /*
     * Get a random user.
     * 
     * @return ResponseEntity with UserEntity.
     */
    @GetMapping("/random")
    public ResponseEntity<UserEntity> getOneRandom() {
        return ResponseEntity.ok(oUserService.getOneRandom());
    }

    /*
     * Create a new user.
     * 
     * @param oUserEntity UserEntity to be created.
     * @return ResponseEntity with the new user's ID.
     */
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody UserEntity oUserEntity) {
        return ResponseEntity.ok(oUserService.create(oUserEntity));
    }

    /*
     * Update an existing user.
     * 
     * @param oUserEntity UserEntity with updated information.
     * @return ResponseEntity with the updated UserEntity.
     */
    @PutMapping("")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity oUserEntity) {
        return ResponseEntity.ok(oUserService.update(oUserEntity));
    }

    /*
     * Delete an existing user by ID.
     * 
     * @param id User's ID to be deleted.
     * @return ResponseEntity with the deleted user's ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserService.delete(id));
    }

    /*
     * Remove all users.
     * 
     * @return ResponseEntity with the number of deleted users.
     */
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oUserService.empty());
    }

    /*
     * Generate random users up to a given amount.
     * 
     * @param amount Number of users to generate.
     * @return ResponseEntity with the number of generated users.
     */
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
