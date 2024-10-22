package by.ustsinovich.fcadhack.exception;

import java.io.Serial;

public class UserNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = 8459244078180277327L;

    public UserNotFoundException(Long id) {
        super("User with ID `%d` not found".formatted(id));
    }

}
