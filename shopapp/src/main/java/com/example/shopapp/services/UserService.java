package com.example.shopapp.services;

import com.example.shopapp.dtos.UserDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.models.Role;
import com.example.shopapp.models.User;
import com.example.shopapp.repositories.RoleRepository;
import com.example.shopapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public User createUser(UserDTO userDTO) throws DataNotFoundException {
        String phoneNumber = userDTO.getPhoneNumber();
        //Kiểm tra số điện thoại tồn tại hay chưa
        if(userRepository.existsByPhoneNumber(phoneNumber))
        {
            throw  new DataIntegrityViolationException("Phong number already exists");
        }
        User newUser = User.builder()
                .fullname(userDTO.getFullName())
                .phoneNumber(phoneNumber)
                .address(userDTO.getAddress())
                .password(userDTO.getPassword())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .build();
        Role role = roleRepository.findById(userDTO.getRoleId()).orElseThrow(()->new DataNotFoundException("Role not found"));
        newUser.setRole(role);
        if(userDTO.getFacebookAccountId()== 0 && userDTO.getGoogleAccountId()== 0)
        {
            String password = userDTO.getPassword();
            //Mã hóa Password
            newUser.setPassword(password);
        }
        return userRepository.save(newUser);
    }

    @Override
    public String login(String numberPhone, String password) {
        return null;
    }
}
