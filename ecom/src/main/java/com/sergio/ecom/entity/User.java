package com.sergio.ecom.entity;

import com.sergio.ecom.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
//@Data - No me estaba funcionando para obtener información en otra clase.
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private UserRole role;

    //Aquí se almacena la imagen del usuario:
    @Lob // Almacena grandes datos
    @Column(columnDefinition = "longblob")
    private byte[] img;

    public User() {
    }

    public User(Long id, String email, String password, String name, UserRole role, byte[] img) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.img = img;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
