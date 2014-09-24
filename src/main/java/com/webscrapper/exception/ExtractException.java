package com.webscrapper.exception;

/** Extract exception class. */
public class ExtractException extends RuntimeException {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -9057507725126285042L;
    /** The message. */
    private String message;

    /** Instantiates a new ExtractException. */
    public ExtractException() {
        super();
    }

    /** Instantiates a new ExtractException.
     * 
     * @param message
     *            the message */
    public ExtractException(final String message) {
        super(message);
        this.message = message;
    }

    /** Instantiates a new ExtractException.
     * 
     * @param throwable
     *            the throwable */
    public ExtractException(final Throwable throwable) {
        super(throwable);
    }

    /** Instantiates a new ExtractException.
     * 
     * @param message
     *            the message
     * @param throwable
     *            the throwable */
    public ExtractException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        return message;
    }
}