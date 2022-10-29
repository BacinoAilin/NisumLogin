package org.login.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.login.api.IUserService;
import org.login.dto.UserRequestDTO;
import org.login.dto.UserResponseDTO;
import org.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * The {@Link UserController} class.
 * configured to work with the {@link UserService} class.
 */
@RestController
@RequestMapping("/UserLogin")
@Validated
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/create-user")
    @Operation(summary = "Creates an user",
            description = "Creates an user, given the parameter fields.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request format",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject("{\n" +
                            "\"name\": \"Ailin Bacino\",\n" +
                            "\"mail\": \"bacino@gmail.com\",\n" +
                            "\"password\": \"2345a\",\n" +
                            "\"phones\": [\n" +
                            "{\n" +
                            "\"number\": \"12345\",\n" +
                            "\"citycode\": \"1\",\n" +
                            "\"countrycode\": \"776\"\n" +
                            "}\n" +
                            "]\n" +
                            "}\n"))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))),
            })
    public Mono<ResponseEntity<UserResponseDTO>> create(@RequestBody final UserRequestDTO userRequestDTO) {
        return userService.create(userRequestDTO)
                .flatMap(dto -> Mono.just(ResponseEntity
                        .ok()
                        .body(dto)));
    }
}
