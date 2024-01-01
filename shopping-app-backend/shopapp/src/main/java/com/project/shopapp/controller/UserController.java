package com.project.shopapp.controller;

import com.project.shopapp.dto.request.UserDTORequest;
import com.project.shopapp.dto.request.UserLoginDTORequest;
import com.project.shopapp.exception.ApiRequestException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
            @Valid @RequestBody UserDTORequest userDTORequest,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .toList();

            throw ApiRequestException.badRequest(errorMsg);
        }

        if (!userDTORequest.getPassword().equals(userDTORequest.getRetypePassword()))
            throw ApiRequestException.exception(List.of("Password dose not match!"),
                    HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok("Register successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserLoginDTORequest userLoginDTORequest,
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
