package com.sergio.ecom.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sergio.ecom.dto.AuthenticationRequest;
import com.sergio.ecom.dto.SignupRequest;
import com.sergio.ecom.dto.UserDto;
import com.sergio.ecom.entity.User;
import com.sergio.ecom.repository.UserRepository;
import com.sergio.ecom.services.auth.AuthService;
import com.sergio.ecom.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {
    // Los controladores manejan las solicitudes HTTP entrantes, las procesa y devuelve las respuestas correspondientes. Los controladores son fundamentales en el patrón de diseño MVC y en aplicaciones RESTful.

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    private final AuthService authService;


    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if (optionalUser.isPresent()){

            response.getWriter().write(new JSONObject()
                    .put("userId", optionalUser.get().getId())
                    .put("role", optionalUser.get().getRole())
                    .toString()

            );

            response.addHeader("Access-Control-Expose-Headers","Authorization");
            response.addHeader("Access-Control-Allow-Headers","Authorization, X-PINGOTHER, Origin, " +
                    "X-Requested-With, Content-Type, Accept, X-Custom-header");

            response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);

        }

    }

    // Este método comprueba si ese usuario ya existe ese Email para ese usuario, en caso de que no exista lo crea. Esto serive para crear la cuenta..
    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        if (authService.hasUserWithEmail(signupRequest.getEmail())){
            return new ResponseEntity<>("User already exists.", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto userDto = authService.createUser(signupRequest);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }



}
