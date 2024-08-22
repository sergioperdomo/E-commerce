package com.sergio.ecom.services.customer.cart;

import com.sergio.ecom.dto.AddProductInCartDto;
import com.sergio.ecom.dto.CartItemsDto;
import com.sergio.ecom.dto.OrderDto;
import com.sergio.ecom.entity.*;
import com.sergio.ecom.enums.OrderStatus;
import com.sergio.ecom.exceptions.ValidationException;
import com.sergio.ecom.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private CouponRepository couponRepository;

    //Método de agregar producto al carrito.
    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus( addProductInCartDto.getUserId(), OrderStatus.Pending);
        // Pasando los artículos.
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndUserId(
                addProductInCartDto.getProductId(),
                addProductInCartDto.getUserId()
                //activeOrder.getId()
        );

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

    public OrderDto getCartByUserId(Long userId) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus( userId, OrderStatus.Pending);
        List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());

        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);

        if (activeOrder.getCoupon() != null) {
            orderDto.setCouponName(activeOrder.getCoupon().getName());
        }

        return orderDto;

    }

    public OrderDto applyCoupon(Long userId, String code) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus( userId, OrderStatus.Pending);
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(() -> new ValidationException("Coupon not found"));

        if (couponIsExpired(coupon)){
            throw new ValidationException("Coupon has expired");
        }

        double discountAmount = ((coupon.getDiscount() / 100.0) * activeOrder.getTotalAmount());
        double netAmount = activeOrder.getTotalAmount() - discountAmount;

        activeOrder.setAmount((long)netAmount);
        activeOrder.setDiscount((long)discountAmount);
        activeOrder.setCoupon(coupon);

        orderRepository.save(activeOrder);
        return activeOrder.getOrderDto();
    }

    private boolean couponIsExpired(Coupon coupon){
        Date currenDate = new Date();
        Date expirationDate = coupon.getExpirationDate();

        return expirationDate != null && currenDate.after(expirationDate);
    }

    public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus( addProductInCartDto.getUserId(), OrderStatus.Pending);
        // Obtenemos el producto por medio del ID.
        Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());

        //Obteniendo los artículos del carrito.
        Optional<CartItems> optionalCartItem = cartItemsRepository.findByProductIdAndUserId(
                addProductInCartDto.getProductId(),
                addProductInCartDto.getUserId()
        );

        if (optionalProduct.isPresent() && optionalCartItem.isPresent()){
            CartItems cartItem = optionalCartItem.get();
            Product product = optionalProduct.get();

            // Actualizando detalles
            activeOrder.setAmount(activeOrder.getAmount() + product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() + product.getPrice());

            cartItem.setQuantity(cartItem.getQuantity() + 1);

            // Verificamos si tenemos cupon en el pedido
            if (activeOrder.getCoupon() != null){
                //Calculo del monto del descuento y el monto neto
                double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;

                activeOrder.setAmount((long)netAmount);
                activeOrder.setDiscount((long)discountAmount);
            }
            cartItemsRepository.save(cartItem);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return null;
    }


    public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus( addProductInCartDto.getUserId(), OrderStatus.Pending);
        // Obtenemos el producto por medio del ID.
        Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());

        //Obteniendo los artículos del carrito.
        Optional<CartItems> optionalCartItem = cartItemsRepository.findByProductIdAndUserId(
                addProductInCartDto.getProductId(),
                addProductInCartDto.getUserId()
        );

        if (optionalProduct.isPresent() && optionalCartItem.isPresent()){
            CartItems cartItem = optionalCartItem.get();
            Product product = optionalProduct.get();

            // Actualizando detalles
            activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());

            cartItem.setQuantity(cartItem.getQuantity() - 1 );

            // Verificamos si tenemos cupon en el pedido
            if (activeOrder.getCoupon() != null){
                //Calculo del monto del descuento y el monto neto
                double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;

                activeOrder.setAmount((long)netAmount);
                activeOrder.setDiscount((long)discountAmount);
            }
            cartItemsRepository.save(cartItem);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return null;
    }

}
