package com.project.shopapp.service;

import com.project.shopapp.dto.request.UserDTORequest;
import com.project.shopapp.dto.request.UserLoginDTORequest;
import com.project.shopapp.dto.response.UserDTOResponse;
import com.project.shopapp.dto.response.UserLoginDTOResponse;

public interface IUserService {

    UserDTOResponse createUser(UserDTORequest userDTORequest);

    UserLoginDTOResponse login(UserLoginDTORequest userLoginDTORequest);
}
