package fh.fa.data.model.error;

import java.util.HashMap;
import java.util.Map;

public class SystemException extends RuntimeException {

    private String source;
    private String sourceCause;
    private ErrorCode errorCode;
    private Map<String, String> details;

    private SystemException(final String source, final String sourceCause, final ErrorCode errorCode, final Map<String, String> details) {
        this.source = source;
        this.sourceCause = sourceCause;
        this.errorCode = errorCode;
        this.details = details;
    }

    public String getSource() {
        return source;
    }

    public String getSourceCause() {
        return sourceCause;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public static Builder newDefault() {
        return new Builder();
    }

    public static Builder newException(final String source, final String sourceCause, final ErrorCode errorCode) {
        return new Builder()
                .source(source)
                .courceCause(sourceCause)
                .errorCode(errorCode);
    }

    public static class Builder {
        private String source;
        private String sourceCause;
        private ErrorCode errorCode;
        private Map<String, String> details;

        Builder() {
            source = "UNKNOWN";
            sourceCause = "UNKNOWN";
            errorCode = ErrorCode.INTERNAL_ERROR;
            details = new HashMap<>();
        }

        public Builder source(final String source) {
            this.source = source;
            return this;
        }

        public Builder courceCause(final String cause) {
            this.sourceCause = cause;
            return this;
        }

        public Builder errorCode(final ErrorCode errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder log(final String key, final String value) {
            this.details.put(key, value);
            return this;
        }

        public Builder log(final Map<String, String> logs) {
            this.details.putAll(logs);
            return this;
        }

        public SystemException build() {
            return new SystemException(source, sourceCause, errorCode, details);
        }
    }
}
