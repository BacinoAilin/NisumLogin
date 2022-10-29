package org.login.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserException extends  RuntimeException {

    private String code;

    private HttpStatus status;

    public UserException() {
        super("The user already exists");
        this.code = "400";
        this.status = HttpStatus.BAD_REQUEST;
    }
}
