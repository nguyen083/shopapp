package com.example.shopapp.controllers;

import com.example.shopapp.dtos.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order-details")
public class OrderDetailController {

    //Thêm mới 1 order detail
    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO) {
        return ResponseEntity.ok("Tạo order detail thành công");
    }

    // Lấy 1 order detail theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok("Get order detail with id " + id);
    }


    // Lấy các order detail của 1 order nào đó
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetailByOrderId(@Valid @PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok("Get order detail with orderid " + orderId);
    }


    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrderDetail(@Valid @PathVariable("orderId") Long orderId, @RequestBody OrderDetailDTO orderDetailDTO) {
        return ResponseEntity.ok("Update order detail with orderid " + orderId );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

}
