package net.ausiasmarch.serverTienda.entity;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private LocalDate birthDate;

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

    @OneToMany(mappedBy = "user", fetch = jakarta.persistence.FetchType.LAZY)
    private List<CartEntity> carts;

    public UserEntity() {
        carts = new ArrayList<>();
    }

    public UserEntity(Long id, String dni, String username, String password, String name, String surname,
            String last_name, LocalDate birthDate, String phone_number, String email, String address, String city,
            int postal_code, Boolean role) {
        this.id = id;
        this.dni = dni;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.last_name = last_name;
        this.birthDate = birthDate;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.role = role;
    }

    public UserEntity(String dni, String username, String password, String name, String surname, String last_name,
            LocalDate birthDate, String phone_number, String email, String address, String city, int postal_code,
            Boolean role) {
        this.dni = dni;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.last_name = last_name;
        this.birthDate = birthDate;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.role = role;
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni (String dni) {
        this.dni = dni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getsurname() {
        return surname;
    }

    public void setsurname (String surname) {
        this.surname = surname;
    }

    public String getlast_name() {
        return last_name;
    }

    public void setlast_name (String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate (LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getphone_number() {
        return phone_number;
    }

    public void setphone_number (String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public int getpostal_code() {
        return postal_code;
    }

    public void setpostal_code (int postal_code) {
        this.postal_code = postal_code;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole (Boolean role) {
        this.role = role;
    }

    public List<CartEntity> getCarts() {
        return carts;
    }

    public void setCarts (List<CartEntity> carts) {
        this.carts = carts;
    }

}
