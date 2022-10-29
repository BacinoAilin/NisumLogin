package org.login.service;


import org.login.api.IUserService;
import org.login.dto.UserRequestDTO;
import org.login.dto.UserResponseDTO;
import org.login.exception.MailException;
import org.login.exception.PasswordException;
import org.login.exception.UserException;
import org.login.model.UserLogin;
import org.login.repository.UserRepository;
import org.login.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

/**
 * The {@link UserService} class implements methods for
 * the {@link IUserService} interface.
 */
@Service
public class UserService implements IUserService {

    /**
     * The repository of
     * the {@link UserLogin} class.
     */
    @Autowired
    UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    @Value("${user.mail.regex.regexp}")
    private String mailRegex;

    @Value("${user.password.regex}")
    private String passwordRegex;



    private boolean regexPattern(final String regexRequest, final String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(regexRequest)
                .matches();
    }
    @Override
    public Mono<UserResponseDTO> create(final UserRequestDTO userRequestDTO) {


        if (userRepository.findByMail(userRequestDTO.getMail()).isPresent()) {
            log.error("this user already exists: " + userRequestDTO.getMail());
            throw new UserException();
        }
        if (!regexPattern(userRequestDTO.getMail(), mailRegex)) {
            log.error("The mail needs to comply with the correct syntax (Example: aaaaaaa@dominio.cl )");
            throw new MailException();
        }
        if (!regexPattern(userRequestDTO.getPassword(), passwordRegex)) {
            log.error("The password needs to comply with the correct syntax");
            throw new PasswordException();
        }
        UserLogin user = userRequestDTO.build();
        user.setToken(new JwtUtil().generateToken(user));

        return Mono.just(user)
                .flatMap(userLogin -> Mono.just(userRepository.save(userLogin)))
                .map(UserResponseDTO::new);

    }
}
