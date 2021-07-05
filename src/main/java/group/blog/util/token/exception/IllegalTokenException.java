package group.blog.util.token.exception;

/**
 * @author Mr.Chen
 **/
public class IllegalTokenException extends Exception {
    private static final long serialVersionUID = -4282501678944024092L;

    public IllegalTokenException() {
    }

    public IllegalTokenException(String message) {
        super(message);
    }

    public IllegalTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}