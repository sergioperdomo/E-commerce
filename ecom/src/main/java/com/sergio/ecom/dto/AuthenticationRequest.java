package com.sergio.ecom.dto;


import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;

    // Para crear el inicio de sesión  se necesita como primer paso la solicitud de autenticación por medio de un dto

}
