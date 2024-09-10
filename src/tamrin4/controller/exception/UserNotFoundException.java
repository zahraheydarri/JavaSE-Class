package tamrin4.controller.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("User not found !!!");
    }
}
