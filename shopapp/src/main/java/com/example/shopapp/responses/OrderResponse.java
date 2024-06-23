package com.example.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OrderResponse extends BaseResponse{
    private Long id;
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("fullname")
    private String fullName;


    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    private String note;
    @JsonProperty("order_date")
    private String orderDate;
    private String status;
    @JsonProperty("total_money")
    private Float totalMoney;

    @JsonProperty("shipping_method")
    private String shippingMethod;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("shipping_date")
    private String shippingDate;

    @JsonProperty("tracking_number")
    private String trackingNumber;

    @JsonProperty("payment_method")
    private String paymentMethod;
    @JsonProperty("active")
    private boolean active; // belong to admin
}
