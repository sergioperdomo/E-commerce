package com.sergio.ecom.services.customer.cart;

import com.sergio.ecom.dto.AddProductInCartDto;
import com.sergio.ecom.entity.CartItems;
import com.sergio.ecom.entity.Order;
import com.sergio.ecom.entity.Product;
import com.sergio.ecom.entity.User;
import com.sergio.ecom.enums.OrderStatus;
import com.sergio.ecom.repository.CartItemsRepository;
import com.sergio.ecom.repository.OrderRepository;
import com.sergio.ecom.repository.ProductRepository;
import com.sergio.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    // Inyectamos el repositorio de pedidos
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    // Inyectamos el repositorio de artículos del carrito.
    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    //Método de agregar producto al carrito.
    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderRepository.findByUserIdAndStatus( addProductInCartDto.getUserId(), OrderStatus.Pending);
        // Pasando los artículos.
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndUserId( addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId() );

        if (optionalCartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());

            if(optionalProduct.isPresent() && optionalUser.isPresent() ){

                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updatedCart = cartItemsRepository.save(cart);

                // Actualizando los detalles del pedido.
                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice() );
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found.");
            }
        }
    }

}
