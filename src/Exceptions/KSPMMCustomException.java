package Exceptions;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class KSPMMCustomException extends Exception {
    private static final long serialVersionUID = 1997753363232807009L;

    public KSPMMCustomException() {
        super();
    }

    public KSPMMCustomException(String errMsg) {
        super(errMsg);
    }

    public KSPMMCustomException(Throwable cause) {
        super(cause);
    }

    public KSPMMCustomException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }


}
