package com.sergio.ecom.dto;

import lombok.Data;

@Data
public class PlaceOrderDto {

    //Realizar el pedido
    private Long userId;
    private String address;
    private String orderDescription;

}
