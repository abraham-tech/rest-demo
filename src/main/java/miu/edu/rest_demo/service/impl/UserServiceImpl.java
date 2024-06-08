package miu.edu.rest_demo.service.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.rest_demo.dto.request.UserRequestDto;
import miu.edu.rest_demo.dto.response.UserResponseDto;
import miu.edu.rest_demo.model.User;
import miu.edu.rest_demo.repository.UserRepository;
import miu.edu.rest_demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<UserResponseDto> createUser(UserRequestDto user) {
        User userEntity = modelMapper.map(user, User.class);
        User savedUser = userRepository.save(userEntity);
        UserResponseDto userResponseDto = modelMapper.map(savedUser, UserResponseDto.class);
        return Optional.of(userResponseDto);
    }

    @Override
    public Optional<UserResponseDto> getUserById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserResponseDto> updatePassword(String username, UserRequestDto userRequestDto) {
        Optional<User> foundUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            user.setPassword(userRequestDto.getPassword());
            User savedUser = userRepository.save(user);
            UserResponseDto userResponseDto = modelMapper.map(savedUser, UserResponseDto.class);
            return Optional.of(userResponseDto);

        }
        return Optional.empty();
    }

    @Override
    public Optional<UserResponseDto> updateUsernameAndPassword(String username, UserRequestDto userRequestDto) {
        Optional<User> foundUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            user.setPassword(userRequestDto.getPassword());
            user.setUsername(userRequestDto.getUsername());
            User savedUser = userRepository.save(user);
            UserResponseDto userResponseDto = modelMapper.map(savedUser, UserResponseDto.class);
            return Optional.of(userResponseDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserResponseDto> getUser(String username) {
        Optional<User> foundUser = Optional.ofNullable(userRepository.findByUsername(username));
        if( foundUser.isPresent()) {
            UserResponseDto userResponseDto = modelMapper.map(foundUser.get(), UserResponseDto.class);
            return Optional.of(userResponseDto);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(String username) {
        Optional<User> foundUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (foundUser.isPresent()) {
            userRepository.delete(foundUser.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<List<UserResponseDto>> findAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            return Optional.empty();
        }
        List<UserResponseDto> userResponseDtos = modelMapper.map(users, new TypeToken<List<UserResponseDto>>() {}.getType());
        return Optional.of(userResponseDtos);
    }
}
