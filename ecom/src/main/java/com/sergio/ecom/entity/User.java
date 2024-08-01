package com.sergio.ecom.entity;

import com.sergio.ecom.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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







}
