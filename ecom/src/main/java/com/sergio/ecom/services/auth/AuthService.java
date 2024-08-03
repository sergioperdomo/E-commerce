package com.sergio.ecom.services.auth;

import com.sergio.ecom.dto.SignupRequest;
import com.sergio.ecom.dto.UserDto;

public interface AuthService {


    UserDto createUser(SignupRequest signupRequest);
    Boolean hasUserWithEmail(String email);





}
