package fh.fa.data.model.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Map;

public class Error {

    private String source;
    private String sourceCause;
    @JsonIgnore
    private transient Throwable exception;

    @JsonUnwrapped
    private ErrorCode errorCode;

    private Map<String, String> details;

    public Error() {
    }

    public Error(final String source, final String cause, final ErrorCode errorCode, final Map<String, String> details) {
        this.source = source;
        this.sourceCause = cause;
        this.errorCode = errorCode;
        this.details = details;
    }

    public String getSource() {
        return source;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public String getSourceCause() {
        return sourceCause;
    }

    public void setSourceCause(final String sourceCause) {
        this.sourceCause = sourceCause;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(final Map<String, String> details) {
        this.details = details;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
