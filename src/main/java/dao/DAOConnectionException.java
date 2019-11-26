package dao;

public class DAOConnectionException extends DAOException {
    /**
     * Constructs a new runtime exception with default detail message.
     */
    public DAOConnectionException() {
        super("Can`t connect to data source for selected DAO.");
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DAOConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public DAOConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
