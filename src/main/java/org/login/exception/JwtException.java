package org.login.exception;

import org.springframework.http.HttpStatus;

public class JwtException extends RuntimeException {

    private String code;

    private HttpStatus status;

    public JwtException() {
        super("Could not verify JWT token");
        this.code = "403";
        this.status = HttpStatus.BAD_REQUEST;
    }
}
