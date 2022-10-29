package org.login.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class MailException extends RuntimeException {


    private String code;

    private HttpStatus status;

    public MailException() {
        super("The email must comply with the correct syntax (Example: aaaaaaa@dominio.cl)");
        this.code = "400";
        this.status = HttpStatus.BAD_REQUEST;
    }
}
