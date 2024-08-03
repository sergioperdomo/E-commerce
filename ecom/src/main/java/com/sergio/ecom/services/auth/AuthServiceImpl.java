package com.sergio.ecom.services.auth;


import com.sergio.ecom.dto.SignupRequest;
import com.sergio.ecom.dto.UserDto;
import com.sergio.ecom.entity.User;
import com.sergio.ecom.enums.UserRole;
import com.sergio.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl  implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserDto createUser(SignupRequest signupRequest){
        User user = new User();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);


        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());

        return userDto;
    }


    public Boolean hasUserWithEmail(String email){
        return  userRepository.findFirstByEmail(email).isPresent();
    }

}
