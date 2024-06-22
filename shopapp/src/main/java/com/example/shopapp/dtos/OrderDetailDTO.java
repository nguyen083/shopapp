package com.example.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1, message = "Order's ID must be greater than 0")
    private Long orderId;

    @JsonProperty("product_id")
    @Min(value = 1, message = "Product's ID must be greater than 0")
    private Long productId;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;

    @Min(value = 0, message = "Price must be greater than or equal 0")
    private Float price;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be greater than or equal 0")
    private Float totalMoney;

    private String color;
}
