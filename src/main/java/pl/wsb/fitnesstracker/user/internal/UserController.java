package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for managing users.
 * Provides CRUD endpoints and additional search operations.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    /**
     * Returns all users with full details.
     *
     * @return list of all users
     */
    @GetMapping
    public List<UserDto> getUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Returns all users with basic info (ID, first name, last name).
     *
     * @return list of simplified user objects
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getSimpleUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserSimpleDto)
                .toList();
    }

    /**
     * Returns a single user by their ID.
     *
     * @param id user ID
     * @return user details
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Returns users whose email contains the given fragment (case-insensitive).
     * Response contains only ID and email.
     *
     * @param email email fragment to search for
     * @return list of matching users
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUsersByEmail(@RequestParam String email) {
        return userProvider.findUsersByEmailFragment(email).stream()
                .map(userMapper::toUserEmailDto)
                .toList();
    }

    /**
     * Returns all users older than (born before) the given date.
     *
     * @param time reference date; users born before this date are returned
     * @return list of matching users
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {
        return userProvider.findUsersOlderThan(time).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Creates a new user.
     *
     * @param userDto user data
     * @return created user
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        return userMapper.toUserDto(userService.createUser(user));
    }

    /**
     * Updates an existing user.
     *
     * @param userId  ID of the user to update
     * @param userDto new user data
     * @return updated user
     */
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        return userMapper.toUserDto(userService.updateUser(userId, user));
    }

    /**
     * Deletes a user by ID.
     *
     * @param userId ID of the user to delete
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}