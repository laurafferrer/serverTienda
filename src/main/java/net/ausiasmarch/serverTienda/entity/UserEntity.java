/*
   Entity class representing a User with attributes, 
   constraints, and relationships.
*/
package net.ausiasmarch.serverTienda.entity;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 9, max = 9)
    @Pattern(regexp = "[0-9]{8}[A-Za-z]{1}")
    private String dni;

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotBlank
    @Size(min = 64, max = 64)
    @Pattern(regexp = "^[a-fA-F0-9]+$")
    private String password = "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e";

    private String tokenPassword;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3,  max = 255)
    private String surname;

    @Size(min = 3,  max = 255)
    private String last_name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birth_date;

    @NotNull
    @Size(min = 9, max = 9)
    @Pattern(regexp = "\\+?[0-9]+")
    private String phone_number;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String address;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String city;

    @NotNull
    private int postal_code;

    private Boolean role = false;

    @JsonManagedReference("user-cart")
    @OneToMany(mappedBy = "user", fetch = jakarta.persistence.FetchType.LAZY)
    private List<CartEntity> carts;

    @JsonManagedReference("user-purchase")
    @OneToMany(mappedBy = "user_id", cascade = CascadeType.PERSIST, fetch = jakarta.persistence.FetchType.LAZY)
    private List<PurchaseEntity> purchases;

    /*
     * Default constructor initializes lists.
     */
    public UserEntity() {
        carts = new ArrayList<>();
    }

    /*
     * Constructor with parameters for full entity initialization.
     * 
     * @param id            User's ID.
     * @param dni           User's DNI.
     * @param username      User's username.
     * @param password      User's password.
     * @param name          User's Name.
     * @param surname       User's surname.
     * @param last_name     User's last name
     * @param birth_date    User's birth date.
     * @param phone_number  User's phone number.
     * @param email         User's email.
     * @param address       User's address.
     * @param city          User's city.
     * @param postal_code   User's postal code.
     * @param role          User's role.
     */
    public UserEntity(Long id, String dni, String username, String password, String name, String surname,
            String last_name, LocalDate birth_date, String phone_number, String email, String address, String city,
            int postal_code, Boolean role) {
        this.id = id;
        this.dni = dni;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.role = role;
    }

    /*
     * Constructor with parameters for partial entity initialization.
     * 
     * @param dni           User's DNI.
     * @param username      User's username.
     * @param password      User's password.
     * @param name          User's Name.
     * @param surname       User's surname.
     * @param last_name     User's last name
     * @param birth_date    User's birth date.
     * @param phone_number  User's phone number.
     * @param email         User's email.
     * @param address       User's address.
     * @param city          User's city.
     * @param postal_code   User's postal code.
     * @param role          User's role.
     */
    public UserEntity(String dni, String username, String password, String name, String surname, String last_name,
            LocalDate birth_date, String phone_number, String email, String address, String city, int postal_code,
            Boolean role) {
        this.dni = dni;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.role = role;
    }

    /*
     * Constructor with parameters for minimal entity initialization.
     * 
     * @param username  User's username.
     * @param password  User's password.
     */
    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /*
     * Get the user's ID.
     * 
     * @return User's ID.
     */
    public Long getId() {
        return id;
    }

    /*
     * Set the user's ID.
     * 
     * @param id    User's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /*
     * Get the user's DNI.
     * 
     * @return User's DNI.
     */
    public String getDni() {
        return dni;
    }

    /*
     * Set the user's DNI.
     * 
     * @param DNI   User's DNI.
     */
    public void setDni (String dni) {
        this.dni = dni;
    }

    /*
     * Get the user's username.
     * 
     * @return User's username.
     */
    public String getUsername() {
        return username;
    }

    /*
     * Set the user's username.
     * 
     * @param username  User's username.
     */
    public void setUsername (String username) {
        this.username = username;
    }

    /*
     * Get the user's password.
     * 
     * @return User's password.
     */
    public String getPassword() {
        return password;
    }

    /*
     * Set the user's password.
     * 
     * @param password  User's password.
     */
    public void setPassword (String password) {
        this.password = password;
    }

    /*
     * Get the user's token password.
     * 
     * @return User's token password.
     */
    public String getTokenPassword() {
        return tokenPassword;
    }

    /*
     * Set the user's token password.
     * 
     * @param tokenPassword User's token password.
     */
    public void setTokenPassword (String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }

    /*
     * Get the user's name.
     * 
     * @return User's name.
     */
    public String getName() {
        return name;
    }

    /*
     * Set the user's name.
     * 
     * @param name  User's name.
     */
    public void setName (String name) {
        this.name = name;
    }

    /*
     * Get the user's surname.
     * 
     * @return User's surname.
     */
    public String getSurname() {
        return surname;
    }

    /*
     * Set the user's surname.
     * 
     * @param surname   User's surname.
     */
    public void setSurname (String surname) {
        this.surname = surname;
    }

    /*
     * Get the user's last name.
     * 
     * @return User's last name.
     */
    public String getLast_name() {
        return last_name;
    }

    /*
     * Set the user's last name.
     * 
     * @param last_name User's last name
     */
    public void setLast_name (String last_name) {
        this.last_name = last_name;
    }

    /*
     * Get the user's birth date
     * 
     * @return User's birth date.
     */
    public LocalDate getBirth_date() {
        return birth_date;
    }

    /*
     * Set the user's birth date
     * 
     * @param birth_date    User's birth date.
     */
    public void setBirth_date (LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    /*
     * Get the user's phone number.
     * 
     * @return User's phone number.
     */
    public String getPhone_number() {
        return phone_number;
    }

    /*
     * Set the user's phone number.
     * 
     * @param phone_number  User's phone number.
     */
    public void setPhone_number (String phone_number) {
        this.phone_number = phone_number;
    }

    /*
     * Get the user's email.
     * 
     * @return User's email.
     */
    public String getEmail() {
        return email;
    }

    /*
     * Set the user's email.
     * 
     * @param email    User's email.
     */
    public void setEmail (String email) {
        this.email = email;
    }

    /*
     * Get the user's address
     * 
     * @return User's address.
     */
    public String getAddress() {
        return address;
    }

    /*
     * Set the user's address
     * 
     * @param address  User's address.
     */
    public void setAddress (String address) {
        this.address = address;
    }

    /*
     * Get the user's city.
     * 
     * @return User's city.
     */
    public String getCity() {
        return city;
    }

    /*
     * Set the user's city.
     * 
     * @param city  User's city.
     */
    public void setCity (String city) {
        this.city = city;
    }

    /*
     * Get the user's postal code.
     * 
     * @return User's postal code.
     */
    public int getPostal_code() {
        return postal_code;
    }
    
    /*
     * Set the user's postal code.
     * 
     * @param postal_code   User's postal code.
     */
    public void setPostal_code (int postal_code) {
        this.postal_code = postal_code;
    }

    /*
     * Get the user's role.
     * 
     * @return User's role.
     */
    public Boolean getRole() {
        return role;
    }

    /*
     * Set the user's role.
     * 
     * @param role. User's role.
     */
    public void setRole (Boolean role) {
        this.role = role;
    }

    /*
     * Get the list of user's carts.
     * 
     * @return list of user's carts.
     */
    public List<CartEntity> getCarts() {
        return carts;
    }

    /*
     * Get the list of user's purchases.
     * 
     * @return list of user's purchases.
     */
    public List<PurchaseEntity> getPurchases() {
        return purchases;
    }

}
