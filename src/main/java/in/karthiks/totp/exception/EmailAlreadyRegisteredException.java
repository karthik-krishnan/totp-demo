package in.karthiks.totp.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Email already registered!!!";
    }
}
