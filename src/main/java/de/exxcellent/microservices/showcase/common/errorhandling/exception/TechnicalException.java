package de.exxcellent.microservices.showcase.common.errorhandling.exception;

import de.exxcellent.microservices.showcase.common.errorhandling.IErrorCode;

/**
 * Exception to use for all technical errors
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public class TechnicalException extends BaseException {
    private static final long serialVersionUID = 612300868258044004L;

    /**
     * Generates a new {@link TechnicalException}.
     *
     * @param errorCode the {@link IErrorCode} of this exception.
     * @param message the error message.
     */
    public TechnicalException(final IErrorCode errorCode, final String message) {
        super(errorCode, message);
    }

    /**
     * Generates a new {@link TechnicalException}.
     *
     * @param errorCode the {@link IErrorCode} of this exception.
     * @param cause the {@link Throwable} of this exception.
     */
    public TechnicalException(final IErrorCode errorCode, final Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * Generates a new {@link TechnicalException}.
     *
     * @param errorCode the {@link IErrorCode} of this exception.
     * @param message the error message.
     * @param cause the {@link Throwable} of this exception.
     */
    public TechnicalException(final IErrorCode errorCode, final String message, final Throwable cause) {
        super(errorCode, message, cause);
    }

}
