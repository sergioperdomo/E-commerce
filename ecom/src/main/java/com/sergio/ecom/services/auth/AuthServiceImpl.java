package com.sergio.ecom.services.auth;


import com.sergio.ecom.dto.SignupRequest;
import com.sergio.ecom.dto.UserDto;
import com.sergio.ecom.entity.Order;
import com.sergio.ecom.entity.User;
import com.sergio.ecom.enums.OrderStatus;
import com.sergio.ecom.enums.UserRole;
import com.sergio.ecom.repository.OrderRepository;
import com.sergio.ecom.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl  implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OrderRepository orderRepository;

    /* este método se encarga de registrar un nuevo usuario, asignarle un rol, crear un pedido inicial asociado a ese usuario
    y devolver un objeto que representa al usuario creado.*/
    public UserDto createUser(SignupRequest signupRequest){
        User user = new User();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);

        Order order = new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        order.setUser(createdUser);
        order.setOrderStatus(OrderStatus.Pending);
        orderRepository.save(order);


        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());

        return userDto;
    }

    /*Este método se utiliza para comprobar si ya existe un usuario en la base de datos con el correo electrónico proporcionado.*/
    public Boolean hasUserWithEmail(String email){
        return  userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct /*se utiliza para indicar que un método debe ser ejecutado después de que se haya completado la inyección de dependencias en un bean*/
    // Método para crear la cuenta de administrador
    public void createAdminAccount(){
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if (null == adminAccount){
            User user = new User();
            user.setEmail("admin@test.com");;
            user.setName("admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }

}
