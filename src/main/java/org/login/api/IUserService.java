package org.login.api;

import org.login.dto.UserRequestDTO;
import org.login.dto.UserResponseDTO;
import org.login.model.UserLogin;
import reactor.core.publisher.Mono;

/**
 * The {@link IUserService} interface represents both a point of access and the behaviour of services
 * associated to the {@link UserLogin}.
 */
public interface IUserService {

    Mono<UserResponseDTO> create (UserRequestDTO userRequestDTO);
}
