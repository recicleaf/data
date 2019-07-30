package fh.fa.data.model.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    INTERNAL_ERROR("INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("NOT_FOUND", HttpStatus.NOT_FOUND),
    ALREADY_EXISTS("ALREADY_EXISTS", HttpStatus.CONFLICT),
    ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT", HttpStatus.BAD_REQUEST);

    private String code;
    private HttpStatus status;

    ErrorCode(final String code, final HttpStatus status) {
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
