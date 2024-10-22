package by.ustsinovich.fcadhack.controller.impl;

import by.ustsinovich.fcadhack.controller.UserController;
import by.ustsinovich.fcadhack.dto.UserDto;
import by.ustsinovich.fcadhack.enums.UserSort;
import by.ustsinovich.fcadhack.filter.UserFilter;
import by.ustsinovich.fcadhack.mapper.UserMapper;
import by.ustsinovich.fcadhack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @Override
    public Page<UserDto> retrieveAllUsers(
            Integer page,
            Integer size,
            UserSort sort,
            UserFilter filter
    ) {
        return userService
                .getAllUsers(page, size, sort, filter)
                .map(userMapper::mapToDto);
    }

    @Override
    public UserDto retrieveUserById(Long id) {
        return userMapper.mapToDto(userService.getUserById(id));
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        return userMapper.mapToDto(userService.updateUser(id, userDto));
    }

    @Override
    public void deleteUserById(Long id) {
        userService.deleteUserById(id);
    }

}
