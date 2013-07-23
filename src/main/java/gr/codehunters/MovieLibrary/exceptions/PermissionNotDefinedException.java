package gr.codehunters.MovieLibrary.exceptions;

public class PermissionNotDefinedException extends RuntimeException {
    public PermissionNotDefinedException(String msg) {
        super(msg);
    }
}
