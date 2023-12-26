package com.project.shopapp.controller;

import com.project.shopapp.dto.UserDTO;
import com.project.shopapp.dto.UserLoginDTO;
import com.project.shopapp.exception.ApiRequestException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .toList();

            // TODO: throw exception to global
            return ResponseEntity.badRequest()
                    .body(errorMsg);

        }

        if (!userDTO.getPassword().equals(userDTO.getRetypePassword()))
            return ResponseEntity.badRequest()
                    .body("Password dose not match!");

        return ResponseEntity.ok("Register successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            // TODO: throw exception to global
            throw ApiRequestException.badRequest(errorMsg);
        }


        // Check user
        // return token
        return ResponseEntity.ok("some token");
    }
}
