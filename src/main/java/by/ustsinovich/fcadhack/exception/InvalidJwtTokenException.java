package by.ustsinovich.fcadhack.exception;

import java.io.Serial;

public class InvalidJwtTokenException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5856120328782175495L;

    public InvalidJwtTokenException() {
        super("Invalid JWT token");
    }

}
