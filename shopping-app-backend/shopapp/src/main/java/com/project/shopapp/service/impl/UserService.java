package com.project.shopapp.service.impl;

import com.project.shopapp.dto.request.UserDTORequest;
import com.project.shopapp.dto.request.UserLoginDTORequest;
import com.project.shopapp.dto.response.UserDTOResponse;
import com.project.shopapp.dto.response.UserLoginDTOResponse;
import com.project.shopapp.entity.User;
import com.project.shopapp.exception.ApiRequestException;
import com.project.shopapp.repository.UserRepository;
import com.project.shopapp.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    UserRepository userRepository;
    ModelMapper mapper;

    @Override
    public UserDTOResponse createUser(UserDTORequest userDTORequest) {
        validateUserDTORequest(userDTORequest);
        User user = mapper.map(userDTORequest, User.class);
        
    }

    @Override
    public UserLoginDTOResponse login(UserLoginDTORequest userLoginDTORequest) {
        return null;
    }

    private void validateUserDTORequest(UserDTORequest userDTORequest) {
        if (userRepository.existsByPhoneNumber(userDTORequest.getPhoneNumber()))
            throw ApiRequestException.badRequest(List.of("Phone number is existed"));
    }

}
