package com.sergio.ecom.dto;

import lombok.Data;

@Data
public class WishListDto {

    private Long UserId;
    private Long ProductId;
    private Long id;
    private String productName;
    private String productDescription;
    private byte[] returnedImg;
    private Long price;

}
