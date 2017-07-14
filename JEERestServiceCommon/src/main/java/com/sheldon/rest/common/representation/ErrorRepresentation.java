package com.sheldon.rest.common.representation;

/**
 * Created by Sheld on 7/13/2017.
 */
public class ErrorRepresentation {
    private Error error;

    public ErrorRepresentation(Integer code, String message) {
        super();
        this.error = new Error(code, message);
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorRepresentation)) return false;

        ErrorRepresentation that = (ErrorRepresentation) o;

        return error != null ? error.equals(that.error) : that.error == null;
    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ErrorRepresentation{" +
                "error=" + error +
                '}';
    }

    public class Error {
        private Integer code;
        private String message;

        public Error() {
            super();
        }

        public Error(Integer code, String message) {
            super();
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Error)) return false;

            Error error = (Error) o;

            if (code != null ? !code.equals(error.code) : error.code != null) return false;
            return message != null ? message.equals(error.message) : error.message == null;
        }

        @Override
        public int hashCode() {
            int result = code != null ? code.hashCode() : 0;
            result = 31 * result + (message != null ? message.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
