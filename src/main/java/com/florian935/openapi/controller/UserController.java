package com.florian935.openapi.controller;

import com.florian935.openapi.domain.User;
import com.florian935.openapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/users")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Tag(name = "user", description = "the user API")
@SecurityRequirement(name = "florian935-security")
public class UserController {

    UserService userService;

    @Operation(summary = "Fetch all users stored in DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users fetched with success", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}, headers = {@Header(name = "authorization", description = "Bearer token to provide", required = true), @Header(name = "content-type", description = "content type of returned response", required = true)}),
            @ApiResponse(responseCode = "500", description = "Internal server error while fetching data", content = {@Content(mediaType = "application/json")})})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    User getUserById(@Parameter(description = "The id of the user that need to be retrieved") @PathVariable String id) {

        return userService.getById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    User saveOneUser(@RequestBody User user) {

        return userService.save(user);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    void deleteUserById(@PathVariable String id) {

        userService.deleteUserById(id);
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    User updateUser(@PathVariable String id, @RequestBody User user) {

        return userService.updateUser(id, user);
    }
}
