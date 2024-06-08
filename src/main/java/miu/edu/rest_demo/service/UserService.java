package miu.edu.rest_demo.service;

import miu.edu.rest_demo.dto.request.UserRequestDto;
import miu.edu.rest_demo.dto.response.UserResponseDto;
import miu.edu.rest_demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserResponseDto> createUser(UserRequestDto user);
    Optional<UserResponseDto> getUserById(Integer id);
    Optional<UserResponseDto> updatePassword(String username, UserRequestDto userRequestDto);
    Optional<UserResponseDto> updateUsernameAndPassword(String username, UserRequestDto userRequestDto);
    Optional<UserResponseDto> getUser(String username);
    boolean deleteUser(String username);
    Optional<List<UserResponseDto>> findAllUsers();
}
