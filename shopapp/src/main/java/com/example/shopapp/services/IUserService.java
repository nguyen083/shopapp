package com.example.shopapp.services;

import com.example.shopapp.exceptions.DataNotFoundException;
import  com.example.shopapp.models.*;
import com.example.shopapp.dtos.UserDTO;
import org.springframework.stereotype.Service;


public interface IUserService {
    User createUser(UserDTO userDTO) throws DataNotFoundException;
    String login(String numberPhone, String password);
}
