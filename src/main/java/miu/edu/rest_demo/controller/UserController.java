package miu.edu.rest_demo.controller;

import lombok.RequiredArgsConstructor;
import miu.edu.rest_demo.dto.request.UserRequestDto;
import miu.edu.rest_demo.dto.response.UserResponseDto;
import miu.edu.rest_demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) {
        Optional<UserResponseDto> userResponseDtoOptional = userService.createUser(userRequestDto);
        if (userResponseDtoOptional.isPresent()) {
            return ResponseEntity.ok(userResponseDtoOptional.get());
        }
//        return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getUser() {
        Optional <List<UserResponseDto>> userResponseDtoList = userService.findAllUsers();
        if (userResponseDtoList.isPresent()) {
            return ResponseEntity.ok(userResponseDtoList.get());
        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{username}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String username) {
        Optional<UserResponseDto> responseDto = userService.getUser(username);
        if (responseDto.isPresent()) {
            return ResponseEntity.ok(responseDto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("{username}")
    public ResponseEntity<UserResponseDto> updatePassword(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        Optional<UserResponseDto> responseDto = userService.updatePassword(username, userRequestDto);
        if (responseDto.isPresent()) {
            return ResponseEntity.ok(responseDto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("{username}")
    public ResponseEntity<UserResponseDto> updateUsernameAndPassword(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        Optional<UserResponseDto> responseDto = userService.updatePassword(username, userRequestDto);
        if (responseDto.isPresent()) {
            return ResponseEntity.ok(responseDto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{username}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable String username) {
        boolean response = userService.deleteUser(username);
        if (response) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
    }

}
