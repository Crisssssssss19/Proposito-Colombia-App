package com.procol.procolombia.auth.exception;


import java.time.LocalDateTime;
import java.util.Map;

public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private Map<String, String> errors;

    // Constructor sin argumentos
    public ApiError() {
    }

    // Constructor con todos los argumentos
    public ApiError(LocalDateTime timestamp, int status, String message, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    // Getters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    // Setters
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiError apiError = (ApiError) o;

        if (status != apiError.status) return false;
        if (timestamp != null ? !timestamp.equals(apiError.timestamp) : apiError.timestamp != null) return false;
        if (message != null ? !message.equals(apiError.message) : apiError.message != null) return false;
        return errors != null ? errors.equals(apiError.errors) : apiError.errors == null;
    }

    // hashCode
    @Override
    public int hashCode() {
        int result = timestamp != null ? timestamp.hashCode() : 0;
        result = 31 * result + status;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (errors != null ? errors.hashCode() : 0);
        return result;
    }

    // toString
    @Override
    public String toString() {
        return "ApiError{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }

    // Patrón Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private LocalDateTime timestamp;
        private int status;
        private String message;
        private Map<String, String> errors;

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder errors(Map<String, String> errors) {
            this.errors = errors;
            return this;
        }

        public ApiError build() {
            return new ApiError(timestamp, status, message, errors);
        }
    }
}
