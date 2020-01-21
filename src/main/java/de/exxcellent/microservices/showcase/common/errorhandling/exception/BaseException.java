package de.exxcellent.microservices.showcase.common.errorhandling.exception;

import de.exxcellent.microservices.showcase.common.errorhandling.IErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;

/**
 * Base exception to be extended by each exception.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
abstract class BaseException extends RuntimeException implements Serializable {
    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = 6957339734842351123L;
    /**
     * The {@link Logger}
     */
    private static final Logger LOG = LoggerFactory.getLogger(BaseException.class);
    /**
     * A message if no stacktrace is available (should usually not happen!)
     */
    private static final String NO_STACKTRACE_AVAILABLE = "[NO STACKTRACE AVAILABLE]";
    /**
     * The {@link IErrorCode} of this exception.
     */
    private final IErrorCode errorCode;

    /**
     * Constructor.
     *
     * @param errorCode the {@link IErrorCode} of this exception.
     * @param message the error message as {@link String}.
     */
    BaseException(final IErrorCode errorCode, final String message) {
        super(getFormattedErrorCode(errorCode) + ": " + message);
        this.errorCode = errorCode;
        LOG.error("{}: {}", getFormattedErrorCode(errorCode), message);
    }

    /**
     * Constructor.
     *
     * @param errorCode the {@link IErrorCode} of this exception.
     * @param cause the {@link Throwable} of this exception.
     */
    BaseException(final IErrorCode errorCode, final Throwable cause) {
        this(errorCode, convertStacktraceToString(cause));
    }

    /**
     * Constructor.
     *
     * @param errorCode the {@link IErrorCode} of this exception.
     * @param message the error message as {@link String}.
     * @param cause the {@link Throwable} of this exception.
     */
    BaseException(final IErrorCode errorCode, final String message, final Throwable cause) {
        this(errorCode, message + System.lineSeparator() + convertStacktraceToString(cause));
    }

    /**
     * Get the {@link IErrorCode} of this exception.
     * @return the {@link IErrorCode} of this exception.
     */
    public IErrorCode getErrorCode() {
        return this.errorCode;
    }

    /**
     * Formats the given {@link IErrorCode} to a human readable {@link String}.
     *
     * @param errorCode the {@link IErrorCode} to format.
     * @return the formatted {@link String}
     */
    private static String getFormattedErrorCode(final IErrorCode errorCode) {
        return "[" + errorCode.getErrorCategory().toString() + "] " + errorCode.getDescription();
    }

    /**
     * Converts the stacktrace of the provided {@link Throwable} to a {@link String}.
     *
     * @param ex the stacktrace of thie {@link Throwable} will be converted.
     * @return the stacktrace as {@link String} or {@link #NO_STACKTRACE_AVAILABLE} if provided {@link Throwable} is {@code null}.
     */
    private static String convertStacktraceToString(final Throwable ex) {
        if(ex != null) {
            final ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ex.printStackTrace(new PrintStream(stream));
            return new String(stream.toByteArray());
        } else {
            return NO_STACKTRACE_AVAILABLE;
        }
    }
}
