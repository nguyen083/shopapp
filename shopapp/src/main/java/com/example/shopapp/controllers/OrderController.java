package com.example.shopapp.controllers;

import com.example.shopapp.dtos.OrderDTO;
import jakarta.validation.Valid;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    //Tạo Order
    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderDTO orderDTO, BindingResult result) {
        try{
            if(result.hasErrors())
            {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Tạo Order thành công");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<?> getOrder( @Valid  @PathVariable Long user_id) {
            try {
                return ResponseEntity.ok("Đây là danh sách order với user_id= " + user_id);
            }catch (Exception e)
                {
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@Valid @PathVariable long id,
                                              @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok("Cập nhật thông tin với id= " + id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@Valid @PathVariable long id) {
        //Xóa mềm cập nhật active = false
        return ResponseEntity.ok("Xóa thông tin với id= " + id);
    }
}
